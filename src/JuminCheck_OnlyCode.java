import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheck_OnlyCode {

	public static void main(String[] args) {
		String juminNum;
		int sum = 0;
		int temp, result;
		int[] weight = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		
		System.out.print("주민번호 입력: ");
		Scanner scan = new Scanner(System.in);
		juminNum = scan.nextLine();
		
		for (int i = 0; i < 13; i++) {
			if(juminNum.charAt(i) == '-') continue;
				sum = sum + (juminNum.charAt(i)-48) * weight[i];		
		}
		
		temp = 11 - (sum%11);
		
		result = temp%10;
		
		if (result == juminNum.charAt(13) - 48) {
			System.out.println("입력한 주민번호는 정상입니다.");
			Calendar cal = Calendar.getInstance(Locale.KOREA);
			int year = cal.get(Calendar.YEAR);
			
			int yy = Integer.parseInt(juminNum.substring(0, 2));
			
			if((juminNum.charAt(7) - 48) < 3) {
				yy = yy + 1900;
			}else {
				yy = yy + 2000;
			}
			int age = year - yy + 1;
			System.out.println("나이: " + age);
			
			if((juminNum.charAt(7) - 48) % 2 == 0) {
				System.out.println("성별: 여자");
			}else {
				System.out.println("성별: 남자");
			}
			
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
			String birthPlace = null;
			
			for (int j = 0; j <= 19; j++) {
				if (locale >= Integer.parseInt(localeCode[j][1]) &&
						locale <= Integer.parseInt(localeCode[j][2])) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("출생지역: " + birthPlace);
			
			System.out.println("생년월일: " + yy + "/" + juminNum.substring(2, 4) + "/" + juminNum.substring(4, 6));
			
			String ddi = null;
			String[] gangi = {"원숭이","닭","개","돼지","쥐","소",
					            "범","토끼","용","뱀","말","양"};
			ddi = gangi[yy%12];
			System.out.println("띠: " + ddi);
		}
		else {
			System.out.println("입력한 주민번호는 틀린 주민번호입니다.");
		}
		
		scan.close();
		
	}
}
