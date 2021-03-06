package com.lec.sts12_validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lec.beans.WriteDTO;

public class BoardValidator implements Validator {

	// 이 Validator가 제공된 Class 의 인스턴스(clazz) 를 유효성 검사 할수 있는가?
	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports(" + clazz.getName() + ")");
		return WriteDTO.class.isAssignableFrom(clazz);
	}

	// 주어진 객체(target)에 유효성 검사를 하고
	// 유효성 검사에 오류가 있는 경우 주어진 객체에 이 오류들을 errors 에 등록한다.
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validate()");
		WriteDTO dto = (WriteDTO)target;
		
		int uid = dto.getUid();
		if(uid == 0) {
			System.out.println("uid 오류");
			// 에러등록 rejectValue(field, errorCode);
			errors.rejectValue("uid", "invalidUid");
			                 // ↑ 반드시! 필드명과 '동일' 해야 한다.
		}
		
		String name = dto.getName();
		if(name == null || name.trim().isEmpty()) {
			System.out.println("name 오류 : 반드시 한글자라도 입력해야 합니다");
			errors.rejectValue("name", "emptyName"); // 에러 등록
		}

		// ValidationUtils 사용
		// 단순히 빈 폼 데이터를 처리할때는 아래 와 같이 사용 가능
		// 두번째 매개변수 "subject" 은 반드시 target 클래스의 필드명 이어야 함
		// 게다가 Errors 에 등록될때도 동일한 field 명으로 등록된다. 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "emptySubject");
		
		
	} // end validate()

}















