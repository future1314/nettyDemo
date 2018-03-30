package simplechat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
/**
 *客户端 channel
 *客户端的处理类比较简单，只需要将读到的信息打印出来即可
 */
public class SimpleChatClientHandler extends  SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
		System.out.println(s);
	}
}
