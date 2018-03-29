package discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * handler 是由 Netty 生成用来处理 I/O 事件的。
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    /**
     * 这里我们覆盖了 chanelRead() 事件处理方法。
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用，这个例子中，收到的消息的类型是 ByteBuf
     */
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	/*
        // 默默地丢弃收到的数据
        ((ByteBuf) msg).release(); // (3)
        */
    	
        /*
        try {
	        // Do something with msg
	    } finally {
	        ReferenceCountUtil.release(msg);
	    }
        */
        
	    ByteBuf in = (ByteBuf) msg;
	    try {
	        while (in.isReadable()) { 
	        	//服务器输出接收到的字符
	        	//这里输入中文会乱码
	            System.out.print((char) in.readByte());
	            System.out.flush();
	        }
	    } finally {
	        ReferenceCountUtil.release(msg); // (2)
	    }
        
    }
   
	/**
	 * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
	 * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
	 */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}