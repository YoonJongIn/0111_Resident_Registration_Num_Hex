import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheck_OnlyCode {

	public static void main(String[] args) {
		String juminNum;
		int sum = 0;
		int temp, result;
		int[] weight = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		
		System.out.print("�ֹι�ȣ �Է�: ");
		Scanner scan = new Scanner(System.in);
		juminNum = scan.nextLine();
		
		for (int i = 0; i < 13; i++) {
			if(juminNum.charAt(i) == '-') continue;
				sum = sum + (juminNum.charAt(i)-48) * weight[i];		
		}
		
		temp = 11 - (sum%11);
		
		result = temp%10;
		
		if (result == juminNum.charAt(13) - 48) {
			System.out.println("�Է��� �ֹι�ȣ�� �����Դϴ�.");
			Calendar cal = Calendar.getInstance(Locale.KOREA);
			int year = cal.get(Calendar.YEAR);
			
			int yy = Integer.parseInt(juminNum.substring(0, 2));
			
			if((juminNum.charAt(7) - 48) < 3) {
				yy = yy + 1900;
			}else {
				yy = yy + 2000;
			}
			int age = year - yy + 1;
			System.out.println("����: " + age);
			
			if((juminNum.charAt(7) - 48) % 2 == 0) {
				System.out.println("����: ����");
			}else {
				System.out.println("����: ����");
			}
			
			String[][] localeCode = {{"����","00","08"},{"�λ�","09","12"},
					                 {"��õ","13","15"},{"���","16","25"},
					                 {"����","26","34"},{"���","35","39"},
					                 {"����","40","40"},{"�泲","41","43"},
					                 {"�泲","45","47"},{"����","44","44"},
					                 {"����","96","96"},{"����","48","54"},
					                 {"����","55","64"},{"����","65","66"},
					                 {"�뱸","67","70"},{"���","71","80"},
					                 {"�泲","81","84"},{"�泲","86","90"},
					                 {"���","85","85"},{"����","91","95"}};
			
			String localeString = juminNum.substring(8, 10);
			int locale = Integer.parseInt(localeString);
			String birthPlace = null;
			
			for (int j = 0; j <= 19; j++) {
				if (locale >= Integer.parseInt(localeCode[j][1]) &&
						locale <= Integer.parseInt(localeCode[j][2])) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("�������: " + birthPlace);
			
			System.out.println("�������: " + yy + "/" + juminNum.substring(2, 4) + "/" + juminNum.substring(4, 6));
			
			String ddi = null;
			String[] gangi = {"������","��","��","����","��","��",
					            "��","�䳢","��","��","��","��"};
			ddi = gangi[yy%12];
			System.out.println("��: " + ddi);
		}
		else {
			System.out.println("�Է��� �ֹι�ȣ�� Ʋ�� �ֹι�ȣ�Դϴ�.");
		}
		
		scan.close();
		
	}
}
