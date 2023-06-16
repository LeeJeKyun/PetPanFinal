package admin.batch.task;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import message.service.face.MessageService;

public class MessageToAllTasklet implements Tasklet {

	@Autowired private MessageService messageService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// 모든 유저에게 메시지를 보내는 로직 영역.
//		System.out.println("모두에게 안녕이라고 보내줘!");
		
//		System.out.println(chunkContext.toString());
		StepContext context = chunkContext.getStepContext();
		Map<String,Object> map = context.getJobParameters();
//		System.out.println(map);
		String content = (String)map.get("content");
		long senduserno = (long) map.get("senduserno");
		int convertedUserno = (int) senduserno;
		messageService.sendMessageToAll(content, convertedUserno);
		
		return RepeatStatus.FINISHED;
	}

}
