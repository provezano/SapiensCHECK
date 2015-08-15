
public class Subject {
	private String subjectName;
	private String lastChange;
	
	public Subject(String subjectName) {
		this.setSubjectName(subjectName);
	}
	
	public Subject(String subjectName, String lastChange) {
		this.setSubjectName(subjectName);
		this.setLastChange(lastChange);
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getLastChange() {
		return lastChange;
	}

	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}
	
	public String toString() {
		return this.getSubjectName() + " | " + this.getLastChange();
	}
	
}
