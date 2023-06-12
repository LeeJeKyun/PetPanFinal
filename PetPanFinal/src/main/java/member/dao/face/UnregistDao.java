package member.dao.face;

import member.dto.Member;

public interface UnregistDao {

	public int selectRegMember(Member member);
	
	/**
	 * 
	 * 탈퇴할 회원이 게시글을 신고했을 경우 게시글 신고를 삭제한다.
	 * @param userno
	 */
	public void deleteReportBoardByUserNo(int userno);
	
	/**
	 * 
	 * 탈퇴할 회원이 신고를 받았을 경우에 게시글 신고를 삭제한다.
	 * @param userno
	 */
	public void deleteReportBoardByBoardNo(int userno);
	
	/**
	 * 	탈퇴할 회원이 댓글 신고를 했을 때 댓글 신고를 삭제한다.
	 * @param userno
	 */
	public void deleteReportCommentByUserNo(int userno);
	
	/**
	 * 탈퇴할 회원이 댓글 신고를 당했을 때 댓글 신고를 삭제한다.
	 * @param userno
	 */
	public void deleteReportCommentByCommentNo1(int userno);
	
	/**
	 * 자신의 게시글에 달린 댓글에 신고가 들어간 경우에 게시글이 지워지기에 그 댓글도 제거한다.
	 * @param userno
	 */
	public void deleteReportCommentByCommentNo2(int userno);
	
	/**
	 * 상품의 신고도 탈퇴할 때 삭제된다.
	 * @param userno
	 */
	public void deleteReportObjectByUserNo(int userno);
	
	/**
	 * 탈퇴시 리뷰할 때 저장했던 파일도 삭제한다.
	 * @param userno
	 */
	public void deleteReviewFileByUserNo(int userno);
	
	/**
	 * 탈퇴시 리뷰를 삭제한다.
	 * @param userno
	 */
	public void deleteReviewByUserNo(int userno);
	
	/**
	 * 탈퇴시 자신의 반려동물의 사진도 삭제한다.
	 * @param userno
	 */
	public void deletePetFileByUserNo(int userno);
	
	/**
	 * 탈퇴시 자신의 반려동물의 정보도 삭제한다.
	 * @param userno
	 */
	public void deletePetByUserNo(int userno);
	
	/**
	 * 탈퇴시 병원일 경우 병원의 정보 파일도 삭제한다.
	 * @param userno
	 */
	public void deleteHospitalFileByUserNo(int userno);
	
	/**
	 * 탈퇴시 병원일 경우 병원의 정보 파일도 삭제한다.
	 * @param userno
	 */
	public void deleteHospitalByUserNo(int userno);
	
	/**
	 * 탈퇴시 블랙이 되어 있는 경우에는 블랙 리스트도 제거한다. (정상적으론 사용될 일이 없다)
	 * @param userno
	 */
	
	public void deleteBlackListByUserNo(int userno);
	
	/**
	 * 장바구니에 저장되어 있는 목록도 삭제한다.
	 * @param userno
	 */
	public void deleteBasketByUserNo(int userno);
	
	/**
	 * 자신이 게시글에 저장했던 파일도 삭제한다.
	 * @param userno
	 */
	public void deleteBoardFileByUserNo(int userno);
	
	/**
	 * 댓글을 적은 사람의 정보를 기본으로 지정되어있는 탈퇴된 회원으로 변경한다.
	 * @param userno
	 */
	public void updateCommentTableByUserNo(int userno);
	
	/**
	 * 자신의 댓글에 추천을 삭제한다.
	 * @param userno
	 */
	public void deleteCommentRecommendByUserNo(int userno);
	
	/**
	 * 자신의 게시글에 달린 댓글들의 추천을 삭제한다.
	 * @param userno
	 */
	public void deleteCommentRecommendBoardByUserNo(int userno);
	
	/**
	 * 자신의 게시글을 추천이 있다면 추천을 삭제한다.
	 * @param userno
	 */
	public void deleteBoardRecommendByUserNo(int userno);
	
	/**
	 * 자신이 추천한 댓글이 있다면 추천한 사람을 변경한다.
	 * @param userno
	 */
	public void updateCommentRecommendByUserNo(int userno);
	
	/**
	 * 자신이 추천한 게시글이 있다면 추천한 사람을 변경한다.
	 * @param userno
	 */
	public void updateBoardRecommendByUserNo(int userno);
	
	/**
	 * 내가 받은 쪽지를 전부 삭제한다.
	 * @param userno
	 */
	public void deleteMessageByUserNo(int userno);
	
	/**
	 * 내가 보낸 쪽지의 유저 정보를 수정한다.
	 * @param userno
	 */
	public void updateMessageByUserNo(int userno);
	
	/**
	 * 구매한 상품이 있다면 구매한 상품의 번호를 삭제한다.
	 * @param userno
	 */
	public void updateOrderByUserNo(int userno);
	
	/**
	 * 자신의 게시글에 써 있는 댓글을 전부 삭제한다.
	 * @param userno
	 */
	public void deleteCommentTableByuserNo(int userno);
	
	/**
	 * 자신의 게시글을 전부 삭제한다.
	 * @param userno
	 */
	public void deleteBoardByUserNo(int userno);
	
	/**
	 * 자신을 삭제한다.
	 * @param userno
	 */
	public void deleteUser(int userno);




	

}
