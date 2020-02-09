package com.nttdata.course.domain;

import java.util.ArrayList;
import java.util.List;

public class CoursePreference {
  private String CourseId;
  private String preference;
 
  List lcp=new ArrayList();
  
  public CoursePreference(String cid, String pre )
  {
	  CourseId=cid;
	  preference=pre;
	
  }

public String getCourseId() {
	return CourseId;
}

public void setCourseId(String courseId) {
	CourseId = courseId;
}

public String getPreference() {
	return preference;
}

public void setPreference(String preference) {
	this.preference = preference;
}

public List getLcp() {
	return lcp;
}

public void setLcp(List lcp) {
	this.lcp = lcp;
}
 
}
  
  
  

