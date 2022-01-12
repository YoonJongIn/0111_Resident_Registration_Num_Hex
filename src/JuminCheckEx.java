import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheckEx {

	public static void main(String[] args) {
		//준비 단계
		String juminNum;
		int sum = 0;
		int temp, result;
		int[] weight = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		
		//입력 단계
		System.out.print("주민번호 입력: ");
		Scanner scan = new Scanner(System.in);
		juminNum = scan.nextLine();
		
		//처리 단계
//		1단계: sum값 구하기
		//"961024-1023411"
		/* [해결방법 1]
		for (int i = 0; i < 13; i++) {  //weight.length => 주민등록번호니깐 13으로 해도 된다. 참이면 if로 내려간다.
			if(juminNum.charAt(i) == '-') {
				continue;
			}else {
				sum = sum + (juminNum.charAt(i)-48) * weight[i];  //{}중괄호 안에 1문장이면 {}를 생략해도된다.				
			}
		}
		*/
		// [해결방법 2]
		for (int i = 0; i < 13; i++) {  //weight.length => 주민등록번호니깐 13으로 해도 된다. 참이면 if로 내려간다.
			if(juminNum.charAt(i) == '-') continue;
				sum = sum + (juminNum.charAt(i)-48) * weight[i];  //{}중괄호 안에 1문장이라면 {}를 생략가능. 1문장 이상이면 생략불가.			
		}
		//System.out.println("sum값 출력: " + sum);
		
//		2단계
		temp = 11 - (sum%11);  //11 모듈러스, temp값이 10 또는 11인 경우가 있다.
		
//		3단계
		result = temp%10;  //3단계 공식은 temp값이 두자리인 경우 한 자리로 만들기 위해서
		
		//주민번호 정상 유무 체크
		if (result == juminNum.charAt(13) - 48) {
			System.out.println("입력한 주민번호는 정상입니다.");
			//정상인 주민번호로부터 정보 추출하기
			//나이 추출 => "시나리오"를 프로그램으로 코딩하기
			//년월일시분초요일 값을 가진 Calendar 클래스 타입의 객체(Instance) get은 가져온다 / set은 세팅한다.
			Calendar cal = Calendar.getInstance(Locale.KOREA);  //Calendar는 클래스객체이기 때문에 new를 안붙인다. 인스턴스는 생성자앞에 new 붙음.
			int year = cal.get(Calendar.YEAR);  //2022 얻어옴. (KOREA, YEAR)는 '필드' 라고함.
			
			/* [2줄로 코딩(초보자)]
			String yyString = juminNum.substring(0, 2);
			int yy = Integer.parseInt(yyString);
			*/
			// [1줄로 코딩(실무)]
			int yy = Integer.parseInt(juminNum.substring(0, 2));  //"96" => 96
			
			if((juminNum.charAt(7) - 48) < 3) {  //1또는 2이면
				yy = yy + 1900;  //1996 얻어옴
				//System.out.println(yy);  //yy값 정상인지 확인한 것
			}else {
				yy = yy + 2000;  //3또는 4이면
			}
			int age = year - yy + 1;  //본나이 => 나중에 "띠"를 얻어내기 위해서
			System.out.println("나이: " + age);
			
//			"성별" 추출하기
			if((juminNum.charAt(7) - 48) % 2 == 0) {  //2또는 4이면 "여자"
				System.out.println("성별: 여자");
			}else {
				System.out.println("성별: 남자");
			}
			
//			"출생지역" 추출하기
			//2차원 배열로 초기화
			String[][] localeCode = {{"서울","00","08"},{"부산","09","12"},
					                 {"인천","13","15"},{"경기","16","25"},
					                 {"강원","26","34"},{"충북","35","39"},
					                 {"대전","40","40"},{"충남","41","43"},
					                 {"충남","45","47"},{"세종","44","44"},
					                 {"세종","96","96"},{"전북","48","54"},
					                 {"전남","55","64"},{"광주","65","66"},
					                 {"대구","67","70"},{"경북","71","80"},
					                 {"경남","81","84"},{"경남","86","90"},
					                 {"울산","85","85"},{"제주","91","95"}};
			
			String localeString = juminNum.substring(8, 10);
			int locale = Integer.parseInt(localeString);
			String birthPlace = null;  //객체가 만들어진게 없으니깐 null
			
			for (int j = 0; j <= 19; j++) {  //localeCode.length = 19
				if (locale >= Integer.parseInt(localeCode[j][1]) &&  //이항연산자 논리곱 && => 양쪽의 조건이 둘다 참일때
						locale <= Integer.parseInt(localeCode[j][2])) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("출생지역: " + birthPlace);
			
//			"생년월일" 추출하기
			System.out.println("생년월일: " + yy + "/" + juminNum.substring(2, 4) + "/" + juminNum.substring(4, 6));
			
//			"띠" 구하기 => 응용 => 출생년도 % 12 나머지 값으로 처리
			String ddi = null;  //객체가 가리키는 값이 없으므로 null
			String[] gangi = {"원숭이","닭","개","돼지","쥐","소",
					            "범","토끼","용","뱀","말","양"};
			ddi = gangi[yy%12];
			System.out.println("띠: " + ddi);
		}else {
			System.out.println("입력한 주민번호는 틀린 주민번호입니다.");
		}
		
		//출력 단계
		scan.close();  //입력 안해줘도 상관없지만 14번에 오류 안뜨게 하기 위한 것
		
	}

}
















