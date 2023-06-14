package admin.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import message.service.face.MessageService;

public class MessageToAllTasklet implements Tasklet {

	@Autowired private MessageService messageService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// 모든 유저에게 메시지를 보내는 로직 영역.
		
		return RepeatStatus.FINISHED;
	}

}
