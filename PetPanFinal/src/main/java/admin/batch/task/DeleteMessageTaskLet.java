package admin.batch.task;

import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import message.service.face.MessageService;

public class DeleteMessageTaskLet implements Tasklet {

	@Autowired private MessageService messageService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// 메시지를 지우는 TaskLet 로직 영역
		
//		System.out.println("5초마다 실행 보여주기 : " + new Date());
		
		messageService.deleteMessageRoutin();
		
		return RepeatStatus.FINISHED;
	}

	 
	
}
