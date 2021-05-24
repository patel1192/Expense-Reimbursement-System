package com.example.model;

public class Reimbursement {
	private int id;
    private Double reimbAmount;;
    private String reimbSubmitted;;
    private String reimbResolved;;
    private String reimbDescription;
    private EmsUser reimbAuthor;;
    private EmsUser reimbResolver;
    public  UserStatus reimbStatus;
	public ErsType reimbType;
	public Reimbursement() {
		
	}
	public Reimbursement(int id, Double reimbAmount, String reimbSubmitted, String reimbResolved, String reimbDescription,
			EmsUser reimbAuthor, EmsUser reimbResolver, UserStatus reimbStatus, ErsType reimbType) {
		super();
		this.id = id;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(Double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public String getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(String reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}
	public String getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(String reimbResolved) {
		this.reimbResolved = reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public EmsUser getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(EmsUser reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public EmsUser getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(EmsUser reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public UserStatus getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(UserStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public ErsType getReimbType() {
		return reimbType;
	}
	public void setReimbType(ErsType reimbType) {
		this.reimbType = reimbType;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reimbAmount=" + reimbAmount + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription + ", reimbAuthor="
				+ reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatus=" + reimbStatus + ", reimbType="
				+ reimbType + "]";
	}
}
