package com.daxia.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateTime
 * @Description: 获取当前日期和时间的工具类
 * @author Alvin
 * @date 2013-6-17 下午3:24:02
 */
public class DateTime {

	/**
	 * 获取当前时间的date形式
	 */
	public Date getDate() {
//		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		java.util.Date time = null;
		try {
			time = dateFormat.parse(dateFormat.format(new Date()));

		} catch (ParseException e) {

			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 得到某年某月的第一天
	 */
	public String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到某年某月的最后一天
	 */
	public String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

}
