package com.syntun.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetSpecification {

	public static void main(String[] args) {
//		Pattern p = Pattern.compile("比\\S*?(喝|性价比)");
//		// 新日期君乐宝开啡尔酸奶200g*12盒，礼盒装常温酸牛奶比安慕希纯甄莫斯利安性价比高
//		Matcher m = p.matcher("柔爱薄款透气拉拉裤 男女宝宝成长学步小内裤 XXL22片*3包");
//		while (m.find()) {
//			System.out.println(m.group());
//		}
		System.out.println(getSpecification("包邮2包包邮伊利学生高锌高钙奶粉营养早餐400克内含16小包") + "__");
	}

	public static HashMap<String, String> getSpecification(String prString) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("product_name", prString.replace("gx", "g"));
		Pattern p = Pattern.compile("(\\d+|\\d+\\.\\d+)(ml|m|g|kg|l|毫升|克|升|千克)");
		Pattern patternYinger = Pattern.compile("(\\d*?)(瓶|听|连包|盒|罐装|罐\\*|杯|袋\\*|袋)(\\d+)(ml|m|g|kg|l|毫升|克|升|千克)");
		Pattern patternMilk = Pattern.compile("(\\d+)\\*(\\d+)ml");
		Pattern patternMilk1 = Pattern.compile("(\\d+)\\*(\\d+)(\\*|×|x|ml\\/|ml|g|克 |千克 |ml/瓶 x )\\*(\\d+)[提|箱]");
		Pattern patternMilk2 = Pattern.compile("(\\d+)(盒×|盒x|盒\\*)(\\d+)");

		Pattern p1n = Pattern.compile("((\\*|×|x|ml\\/|ml|g |克 |千克 |ml/瓶 x )\\d+(\\*|×)\\d*?)");
		Pattern p2n = Pattern.compile("\\d+[ml|\\*|g]+(\\d+)");
		Pattern p1 = Pattern.compile("((\\*|×|x|ml\\/|ml|g |克 |千克 |ml/瓶 x )\\d+)[^ml|m|g|kg|l|毫升|克|升|千克|-|/箱]");
		Pattern p2 = Pattern.compile(
				"(\\d+)(瓶|\\*|瓶\\*|支|罐/件|瓶x|桶\\*|盒\\*|支\\*|罐\\*|罐/件\\*|ml/瓶x|罐x|罐/箱\\*|瓶/箱\\*|/箱整箱x|听/箱\\*|桶/箱x|盒/箱整箱x|\\*)(\\d+)[^ml|m|g|kg|l|毫升|克|升|千克]");
		Pattern p3 = Pattern.compile("(\\d+)(罐|瓶\\*|瓶X|瓶x|罐/件\\*|罐/件|盒×)(\\d+)[ml|箱|纯]");
		Pattern p4n = Pattern.compile("\\d+[\\*|x|]\\d+");
		Pattern p4 = Pattern.compile("\\d+");
		Pattern pOne = Pattern.compile("[（|\\(](\\d+)\\*(\\d+)(ml|m|g|kg|l|毫升|克|升|千克)");
		Pattern pT = Pattern.compile("(\\d+)[瓶|听|连包|盒|罐装|罐\\*|杯|袋]");
		String prodcutName = data.get("product_name").replace("*1*", "").replace("x1x", "");
		Matcher matcher = p.matcher(prodcutName.toLowerCase());
		while (matcher.find()) {
			String value1 = matcher.group(1);
			String value2 = matcher.group(2);
			String value = matcher.group(0).replace("克", "g").replace("毫升", "mg");
			if (!value.contains("ml") && value.contains("m")) {
				value = value.replace("m", "ml");
			}
			boolean isTrue = false;
			switch (value2) {
			case "千克":
				value = (Double.parseDouble(value1) * 1000 + "g").replace(".0", "");
				break;
			case "升":
				value = (Double.parseDouble(value1) * 1000 + "ml").replace(".0", "");
				break;
			case "kg":
				value = (Double.parseDouble(value1) * 1000 + "g").replace(".0", "");
				break;
			case "l":
				value = (Double.parseDouble(value1) * 1000 + "ml").replace(".0", "");
				break;
			default:
				isTrue = true;
				break;
			}
			if (prodcutName.contains("(" + value) || value.indexOf("0") == 0) {
				continue;
			}
			if (data.containsKey("specification")) {
				if (!data.get("specification").equals(value)) {
					data.put("specification", data.get("specification") + "@" + value);
					if (isTrue) {
						data.put("specification_str", data.get("specification_str") + "@" + value1);
					} else {
						data.put("specification_str", data.get("specification_str") + "@"
								+ ((Double.parseDouble(value1) * 1000) + "").replace(".0", ""));
					}
				}
			} else {
				if (isTrue) {
					data.put("specification_str", value1);
				} else {
					data.put("specification_str", ((Double.parseDouble(value1) * 1000) + "").replace(".0", ""));
				}
				data.put("specification", value);
			}
		}
		String oldSpecification = "";
		StringBuffer specification = new StringBuffer(oldSpecification);
		Matcher matcher1n = p1n.matcher(prodcutName.toLowerCase() + "dd");
		boolean isFirst = true;
		while (matcher1n.find()) {
			if (prodcutName.contains(matcher1n.group(1) + "克") || prodcutName.contains(matcher1n.group(1) + "0克")) {
				continue;
			}
			String value = matcher1n.group(1).replace("ml", "x").replace("ml/", "x");
			isFirst = false;
			specification.append("规格=").append(value);
		}
		Matcher mcMilk2 = patternMilk2.matcher(prodcutName.toLowerCase() + "dd");
		while (mcMilk2.find()) {
			if (prodcutName.contains(mcMilk2.group(1) + "克") || prodcutName.contains(mcMilk2.group(1) + "0克")) {
				continue;
			}
			String value = mcMilk2.group(1).replace("ml", "x").replace("ml/", "x") + "*"
					+ mcMilk2.group(3).replace("ml", "x").replace("ml/", "x");
			isFirst = false;
			specification.append("规格=").append(value);
		}

		if (isFirst) {
			Matcher mcMilk1 = patternMilk1.matcher(prodcutName.toLowerCase() + "dd");
			while (mcMilk1.find()) {
				if (prodcutName.contains(mcMilk1.group(1) + "克") || prodcutName.contains(mcMilk1.group(1) + "0克")) {
					continue;
				}
				String value = mcMilk1.group(1).replace("ml", "x").replace("ml/", "x") + "*"
						+ mcMilk1.group(4).replace("ml", "x").replace("ml/", "x");
				isFirst = false;
				specification.append("规格=").append(value);
			}
		}
		if (isFirst) {
			Matcher matcher2n = p2n.matcher(prodcutName.toLowerCase() + "dd");
			while (matcher2n.find()) {
				if (prodcutName.contains(matcher2n.group(1) + "克") || prodcutName.contains(matcher2n.group(1) + "0克")) {
					continue;
				}
				String value = matcher2n.group(1).replace("ml", "x").replace("ml/", "x");
				isFirst = false;
				specification.append("规格=").append(value);
			}
		}

		//System.out.println(specification);

		Matcher mcMilk = patternMilk.matcher(prodcutName.toLowerCase() + "dd");
		while (mcMilk.find()) {
			if (prodcutName.contains(mcMilk.group(1) + "克") || prodcutName.contains(mcMilk.group(1) + "0克")) {
				continue;
			}
			String value = mcMilk.group(1).replace("ml", "x").replace("ml/", "x");
			isFirst = false;
			specification.append("规格=").append(value);
		}
		if (isFirst) {
			Matcher matcher1 = p1.matcher(prodcutName.toLowerCase() + "dd");
			while (matcher1.find()) {
				if (prodcutName.contains(matcher1.group(1) + "克") || prodcutName.contains(matcher1.group(1) + "0克")) {
					continue;
				}
				String value = matcher1.group(1).replace("ml", "x").replace("ml/", "x");
				specification.append("规格=").append(value);
			}
		}

		if (specification.toString().split("规格=").length > 2 || specification.toString().equals(oldSpecification)) {
			Matcher matcher2 = p2.matcher(prodcutName.toLowerCase() + "dd");
			while (matcher2.find()) {
				String value = oldSpecification + matcher2.group(1) + "*" + matcher2.group(3);
				if (prodcutName.contains(matcher2.group(3) + "克") || prodcutName.contains(matcher2.group(3) + "0克")) {
					continue;
				}
				specification = new StringBuffer(value);
			}

		} else if (specification.toString().split("规格=").length == 2) {
			Matcher matcher3 = p3.matcher(prodcutName.toLowerCase() + "dd");
			while (matcher3.find()) {
				String value = oldSpecification + matcher3.group(1) + "*" + matcher3.group(3);
				if (matcher3.group(3).equals("1")) {
					value = oldSpecification + matcher3.group(1);
				}
				if (matcher3.group(1).equals("1")) {
					value = oldSpecification + matcher3.group(3);
				}
				if (prodcutName.contains(matcher3.group(3) + "ml")) {
					value = oldSpecification + matcher3.group(1);
				}
				if (prodcutName.contains(matcher3.group(3) + "0ml")) {
					value = oldSpecification + matcher3.group(1);
				}
				specification = new StringBuffer(value);
			}
		}

		Matcher matcherOne = pOne.matcher(prodcutName.toLowerCase());
		while (matcherOne.find()) {
			specification = new StringBuffer(matcherOne.group(1));

		}
		if (specification.toString().equals("")) {
			Matcher matcherT = pT.matcher(prodcutName.toLowerCase());
			while (matcherT.find()) {
				if (!specification.toString().equals("")) {
					specification.append(" 多个瓶数 ").append(matcherT.group(1));
				} else {
					specification = new StringBuffer(matcherT.group(1));
				}
			}
		}

		String specificationValue = specification.toString();
		String[] twoStrs = specificationValue.split("规格=");
		String oldTwo = "规格=";
		for (String two : twoStrs) {
			if (oldTwo.equals("规格=")) {
				oldTwo = two;
			} else if (!oldTwo.equals("规格=" + two)) {
				oldTwo = oldTwo + "规格=" + two;
			}
		}
		specificationValue = oldTwo;
		if (specificationValue.toString().split("规格=").length == 2) {
			Matcher mp4n = p4n.matcher(specificationValue);
			boolean isN = true;
			while (mp4n.find()) {
				specificationValue = mp4n.group(0);
				isN = false;
			}
			if (isN) {
				Matcher mp4 = p4.matcher(specificationValue);
				while (mp4.find()) {
					specificationValue = mp4.group(0);
				}
			}
		}

		data.put("specification_two", specificationValue);

		if (specificationValue.equals("")) {
			if (data.get("specification") != null && !data.get("specification").equals("")) {
				data.put("specification_end", data.get("specification"));
			} else {
				data.put("specification_end", "");
			}
		} else if (data.get("specification") != null && !data.get("specification").equals("")) {
			data.put("specification_end", data.get("specification") + "*" + data.get("specification_two"));
		} else {
			data.put("specification_end", "");
		}

		Matcher my = patternYinger.matcher(prodcutName.toLowerCase());
		if (my.find()) {

			String value1 = my.group(3);
			String value2 = my.group(4);
			String value = (value1 + value2).replace("克", "g").replace("毫升", "mg");
			if (!value.contains("ml") && value.contains("m")) {
				value = value.replace("m", "ml");
			}
			switch (value2) {
			case "千克":
				value = (Double.parseDouble(value1) * 1000 + "g").replace(".0", "");
				break;
			case "升":
				value = (Double.parseDouble(value1) * 1000 + "ml").replace(".0", "");
				break;
			case "kg":
				value = (Double.parseDouble(value1) * 1000 + "g").replace(".0", "");
				break;
			case "l":
				value = (Double.parseDouble(value1) * 1000 + "ml").replace(".0", "");
				break;
			default:
				break;
			}

			data.put("specification_end", value + "*" + my.group(1));
			data.put("specification_str", value);
			data.put("specification_two", my.group(1));
			data.put("specification", value);

		}

		return data;
	}

	public static boolean isEquals(String a, String b) {
		if (a.equals(b + "*1") || b.equals(a + "*1")) {
			return true;
		}
		return false;
	}

}
