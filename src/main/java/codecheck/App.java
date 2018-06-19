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

			StringBuilder returnStrungBuilder = new StringBuilder();

			for (int i = 0; i < args[1].length(); i++) {
				returnStrungBuilder.append(Integer
						.toString(8 - Integer.parseInt(args[1].substring(i, i + 1).replace('A', '0').replace('B', '1')
								.replace('C', '2').replace('D', '3')
								.replace('E', '4').replace('F', '5').replace('G', '6').replace('H', '7')
								.replace('I', '8')))
						.replace('0', 'A').replace('1', 'B').replace('2', 'C').replace('3', 'D')
						.replace('4', 'E').replace('5', 'F').replace('6', 'G').replace('7', 'H').replace('8', 'I'));

			}

			resultStr = returnStrungBuilder.toString();

		}

		//結果出力
		System.out.println(resultStr);

	}

	static private String encode(String num10) {

		StringBuilder convertStringBuilder = new StringBuilder(num10);
		int addNum = 0;
		int taegrtNum = 0;

		for (int i = 0; i < num10.length(); i++) {

			taegrtNum = Integer.parseInt(num10.substring(i, i + 1)) + addNum;

			if (taegrtNum >= 9) {
				addNum = 1;
				convertStringBuilder.append(taegrtNum - 9);

			} else {
				addNum = 0;
				convertStringBuilder.append(taegrtNum);

			}

		}

		//終わったら反転
		String num9 = convertStringBuilder.reverse().toString();

		//アルファベットに変換
		num9 = num9.replace('0', 'A').replace('1', 'B').replace('2', 'C').replace('3', 'D')
				.replace('4', 'E').replace('5', 'F').replace('6', 'G').replace('7', 'H').replace('8', 'I');

		return num9;

	}

	static private String decode(String num9) {

		StringBuilder convertStringBuilder = new StringBuilder();
		int addNum = 0;
		int taegrtNum = 0;

		StringBuilder tmpSB = new StringBuilder(
				num9.replace('A', '0').replace('B', '1').replace('C', '2').replace('D', '3')
						.replace('E', '4').replace('F', '5').replace('G', '6').replace('H', '7').replace('I', '8'));

		//重みづけして端数を下位に下す
		for (int i = 0; i < tmpSB.length(); i++) {
			if (i < tmpSB.length() - 1) {
				taegrtNum = Integer.parseInt(tmpSB.substring(i, i + 1)) + addNum;
				if (taegrtNum >= 10) {
					addNum = 1;
					convertStringBuilder.append(taegrtNum - 10);
				} else {
					addNum = 0;
					convertStringBuilder.append(taegrtNum);
				}
			} else {
				taegrtNum = Integer.parseInt(tmpSB.substring(i, i + 1)) * 9 + addNum;

				addNum = taegrtNum % 10;
				convertStringBuilder.append(taegrtNum / 10);
			}
		}

		//1桁目が超えてしまったら逆順処理で繰り上げる
		if (addNum != 0) {
			tmpSB = convertStringBuilder.reverse();
			convertStringBuilder = new StringBuilder();

			for (int i = 0; i < tmpSB.length(); i++) {
				if (i == 0) {
					convertStringBuilder.append(tmpSB.substring(i, i + 1));
				} else {
					taegrtNum = Integer.parseInt(tmpSB.substring(i, i + 1)) + addNum;
					if (taegrtNum >= 10) {
						addNum = 1;
						convertStringBuilder.append(taegrtNum - 10);
					} else {
						addNum = 0;
						convertStringBuilder.append(taegrtNum);
					}
				}
			}
		}

		//String変換
		return convertStringBuilder.reverse().toString();

	}

}
