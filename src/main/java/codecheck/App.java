package codecheck;

public class App {
	public static void main(String[] args) {

		//CLIアプリケーションには2つの引数が渡されます。
		//不正な引数が渡されることはありません。
		//とはいえやっとかないと気持ち悪い
		if (args == null) {
			System.out.println("Error01!");
			return;
		} else if (args.length != 2) {
			System.out.println("Error02!");
			return;
		}

		//引数チェック
		//第1引数にはサブコマンドが渡されます。
		// サブコマンドはencode・decode・alignのいずれかです。
		if ("encode".equals(args[0])) {
			// サブコマンドがencodeの場合、引数には10進数の数値が渡されます。
			//if (!args[1].matches("^[0-9]*$")) {
			//	System.out.println("Error06!");
			//	return;
			//}
		} else if ("decode".equals(args[0])) {
			// サブコマンドがdecodeまたはalignの場合、引数にはアルファベット数字が渡されます。
			//if (!args[1].matches("[B-I].[A-I]*$")) {
			//	System.out.println("Error07!");
			//	return;
			//}
		} else if ("align".equals(args[0])) {
			// サブコマンドがdecodeまたはalignの場合、引数にはアルファベット数字が渡されます。
			//if (!args[1].matches("[B-I],[A-I]*$")) {
			//	System.out.println("Error08!");
			//	return;
			//}
		} else {
			System.out.println("Error03!");
			return;
		}

		//9進数の変換処理

		String resultStr = "";

		//数字からの変換
		if ("encode".equals(args[0])) {
			//10進数の数値をアルファベット数字に変換した結果を表示してください。
			//int超える対応は面倒なのであきらめ…
			try {

				resultStr = encode(args[1]);

			} catch (Exception e) {
				System.out.println("Error04!");
			}

		} else if ("decode".equals(args[0])) {

			try {

				resultStr = decode(args[1]);

			} catch (Exception e) {
				System.out.println("Error05!");
			}

		} else {
			//引数のアルファベット数字に対して、合計の数字がすべてHになるような足し算の数式をアルファベット数字で表示してください。

			//引数と同等の桁数のH埋め文字列を作成
			String targetNum = "";
			for (int i = 0; i < args[1].length(); i++) {
				targetNum = targetNum + "H";
			}

			//作成した文字列と引数を10進数に戻し、差し引きした結果をさらに9進数に戻す

			resultStr = encode(Integer.toString(Integer.parseInt(targetNum) - Integer.parseInt(decode(args[1]))));

		}

		//結果出力
		System.out.println(resultStr);

	}

	static private String encode(String num10) {

		StringBuilder outStringBuilder = new StringBuilder();
		String num9;

		//まずは9進数(数字）に変換
		int targetInt = Integer.parseInt(num10);
		int amari = targetInt % 9;
		int syou = targetInt / 9;
		outStringBuilder.append(Integer.toString(amari));
		//割り切れるまで繰り返す
		while (syou != 0) {
			amari = syou % 9;
			outStringBuilder.append(Integer.toString(amari));
			syou = syou / 9;
		}
		//逆順に積まれてるので反転して正しい桁数にする
		num9 = outStringBuilder.reverse().toString();

		//アルファベットに変換
		num9 = num9.replace('0', 'A').replace('1', 'B').replace('2', 'C').replace('3', 'D')
				.replace('4', 'E').replace('5', 'F').replace('6', 'G').replace('7', 'H').replace('8', 'I');

		return num9;

	}

	static private String decode(String num9) {

		StringBuilder outStringBuilder = new StringBuilder();

		//アルファベット数字を10進数に変換した結果を表示してください
		//まずは9進数(数字）に変換
		outStringBuilder = new StringBuilder(
				num9.replace('A', '0').replace('B', '1').replace('C', '2').replace('D', '3')
						.replace('E', '4').replace('F', '5').replace('G', '6').replace('H', '7').replace('I', '8'));
		//逆順処理するので反転
		outStringBuilder = outStringBuilder.reverse();

		int resultNum = 0;
		int omomiNum = 1;
		//1ループごとに重みを乗算する
		for (int i = 0; i < outStringBuilder.length(); i++) {
			resultNum = resultNum + Integer.parseInt(outStringBuilder.substring(i, i + 1)) * omomiNum;
			omomiNum = omomiNum * 9;
		}
		//String変換
		return Integer.toString(resultNum);

	}

}
