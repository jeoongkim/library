package library.model.vo;

public enum AuthorityId 
{
	ADMIN(10),		// 관리자
	USER(20),		// 일반유저
	BREAKUSERS(30), // 가입초기& 불량유저
	UNUSERS(40);	// 탈퇴유저
	
	private Integer id;
	
	private AuthorityId(Integer id) 
	{
		this.id=id;
	}
	public Integer getAuthorityId() 
	{
		return this.id;
	}
	
}
