import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheckEx {

	public static void main(String[] args) {
		//�غ� �ܰ�
		String juminNum;
		int sum = 0;
		int temp, result;
		int[] weight = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		
		//�Է� �ܰ�
		System.out.print("�ֹι�ȣ �Է�: ");
		Scanner scan = new Scanner(System.in);
		juminNum = scan.nextLine();
		
		//ó�� �ܰ�
//		1�ܰ�: sum�� ���ϱ�
		//"961024-1023411"
		/* [�ذ��� 1]
		for (int i = 0; i < 13; i++) {  //weight.length => �ֹε�Ϲ�ȣ�ϱ� 13���� �ص� �ȴ�. ���̸� if�� ��������.
			if(juminNum.charAt(i) == '-') {
				continue;
			}else {
				sum = sum + (juminNum.charAt(i)-48) * weight[i];  //{}�߰�ȣ �ȿ� 1�����̸� {}�� �����ص��ȴ�.				
			}
		}
		*/
		// [�ذ��� 2]
		for (int i = 0; i < 13; i++) {  //weight.length => �ֹε�Ϲ�ȣ�ϱ� 13���� �ص� �ȴ�. ���̸� if�� ��������.
			if(juminNum.charAt(i) == '-') continue;
				sum = sum + (juminNum.charAt(i)-48) * weight[i];  //{}�߰�ȣ �ȿ� 1�����̶�� {}�� ��������. 1���� �̻��̸� �����Ұ�.			
		}
		//System.out.println("sum�� ���: " + sum);
		
//		2�ܰ�
		temp = 11 - (sum%11);  //11 ��ⷯ��, temp���� 10 �Ǵ� 11�� ��찡 �ִ�.
		
//		3�ܰ�
		result = temp%10;  //3�ܰ� ������ temp���� ���ڸ��� ��� �� �ڸ��� ����� ���ؼ�
		
		//�ֹι�ȣ ���� ���� üũ
		if (result == juminNum.charAt(13) - 48) {
			System.out.println("�Է��� �ֹι�ȣ�� �����Դϴ�.");
			//������ �ֹι�ȣ�κ��� ���� �����ϱ�
			//���� ���� => "�ó�����"�� ���α׷����� �ڵ��ϱ�
			//����Ͻú��ʿ��� ���� ���� Calendar Ŭ���� Ÿ���� ��ü(Instance) get�� �����´� / set�� �����Ѵ�.
			Calendar cal = Calendar.getInstance(Locale.KOREA);  //Calendar�� Ŭ������ü�̱� ������ new�� �Ⱥ��δ�. �ν��Ͻ��� �����ھտ� new ����.
			int year = cal.get(Calendar.YEAR);  //2022 ����. (KOREA, YEAR)�� '�ʵ�' �����.
			
			/* [2�ٷ� �ڵ�(�ʺ���)]
			String yyString = juminNum.substring(0, 2);
			int yy = Integer.parseInt(yyString);
			*/
			// [1�ٷ� �ڵ�(�ǹ�)]
			int yy = Integer.parseInt(juminNum.substring(0, 2));  //"96" => 96
			
			if((juminNum.charAt(7) - 48) < 3) {  //1�Ǵ� 2�̸�
				yy = yy + 1900;  //1996 ����
				//System.out.println(yy);  //yy�� �������� Ȯ���� ��
			}else {
				yy = yy + 2000;  //3�Ǵ� 4�̸�
			}
			int age = year - yy + 1;  //������ => ���߿� "��"�� ���� ���ؼ�
			System.out.println("����: " + age);
			
//			"����" �����ϱ�
			if((juminNum.charAt(7) - 48) % 2 == 0) {  //2�Ǵ� 4�̸� "����"
				System.out.println("����: ����");
			}else {
				System.out.println("����: ����");
			}
			
//			"�������" �����ϱ�
			//2���� �迭�� �ʱ�ȭ
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
			String birthPlace = null;  //��ü�� ��������� �����ϱ� null
			
			for (int j = 0; j <= 19; j++) {  //localeCode.length = 19
				if (locale >= Integer.parseInt(localeCode[j][1]) &&  //���׿����� ���� && => ������ ������ �Ѵ� ���϶�
						locale <= Integer.parseInt(localeCode[j][2])) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("�������: " + birthPlace);
			
//			"�������" �����ϱ�
			System.out.println("�������: " + yy + "/" + juminNum.substring(2, 4) + "/" + juminNum.substring(4, 6));
			
//			"��" ���ϱ� => ���� => ����⵵ % 12 ������ ������ ó��
			String ddi = null;  //��ü�� ����Ű�� ���� �����Ƿ� null
			String[] gangi = {"������","��","��","����","��","��",
					            "��","�䳢","��","��","��","��"};
			ddi = gangi[yy%12];
			System.out.println("��: " + ddi);
		}else {
			System.out.println("�Է��� �ֹι�ȣ�� Ʋ�� �ֹι�ȣ�Դϴ�.");
		}
		
		//��� �ܰ�
		scan.close();  //�Է� �����൵ ��������� 14���� ���� �ȶ߰� �ϱ� ���� ��
		
	}

}
















