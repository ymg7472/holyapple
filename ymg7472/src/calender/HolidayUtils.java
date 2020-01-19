package calender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.common.collect.Lists;
import com.ibm.icu.util.ChineseCalendar;

import scala.Tuple2;
 
public class HolidayUtils {
 
    public Map<String, String> solarHolidayMap = new HashMap<String, String>();
    public Map<String, String> lunarHolidayMap = new HashMap<String, String>();
 
    public String getDateByString(Date date) {
        return getDateByString(date, "-");
    }
 
    public String getDateByString(Date date, String separator) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+separator+"MM"+separator+"dd");
        return sdf.format(date);
    }
 
    /**
     * 양력날짜를 음력날짜로 변환
     * @param 양력날짜 (yyyyMMdd)
     * @return 음력날짜 (yyyyMMdd)
     */
//    private String converSolarToLunar(String date) {
//        return converSolarToLunar(date, "-");
//    }
 
    private String converSolarToLunar(String date, String separator) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();
 
        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
 
        cc.setTimeInMillis(cal.getTimeInMillis());
 
        int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
        int m = cc.get(ChineseCalendar.MONTH) + 1;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH);
 
        StringBuffer ret = new StringBuffer();
        ret.append(String.format("%04d", y)).append(separator);
        ret.append(String.format("%02d", m)).append(separator);
        ret.append(String.format("%02d", d));
 
        return ret.toString();
    } // end converSolarToLunar
 
    public String getDay(String date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6)));
        cal.add(Calendar.DAY_OF_MONTH, amount);
 
        return getDateByString(cal.getTime(), "");
    }
    
    /**
     * 해당일자가 대체공휴일에 해당하는 지 확인
     * @param 양력날짜 (yyyyMMdd)
     * @return 대체 공휴일이면 true
     */
    private boolean isHolidayAlternate(String date) {
        
        String[] altHoliday = new String[] {
                "20150929", "20160210", "20170130", "20180926", 
                "20180507", "20190506", "20200127", "20220912", 
                "20230124", "20240212", "20240506", "20251008", 
                "20270209", "20290924", "20290507"}; 
        
        return Arrays.asList(altHoliday).contains(date); 
        
        /*
        int year = Integer.parseInt(date.substring(0, 4));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        
        // 설날
        String dayFirst2 = convertLunarToSolar(year + "0101");
        String dayFirst3 = convertLunarToSolar(year + "0102");
        String dayFirst1 = String.valueOf(Integer.parseInt(dayFirst2) - 1);     
        
        // 추석
        String dayThanks1 = convertLunarToSolar(year + "0814");
        String dayThanks2 = convertLunarToSolar(year + "0815");
        String dayThanks3 = convertLunarToSolar(year + "0816");
        
        // 어린이날
        String dayChild = year + "0505";
        
        // 해당 년도의 대체휴일 목록
        List<String> altHolyday = new ArrayList<String>();
        
        if(getDayOfWeek(dayFirst1) == Calendar.SUNDAY || getDayOfWeek(dayFirst2) == Calendar.SUNDAY || getDayOfWeek(dayFirst3) == Calendar.SUNDAY || isHolidaySolar(dayFirst1) || isHolidaySolar(dayFirst2) || isHolidaySolar(dayFirst3)) {
            int y = Integer.parseInt(dayFirst3.substring(0, 4));
            int m = Integer.parseInt(dayFirst3.substring(4, 6)) - 1;
            int d = Integer.parseInt(dayFirst3.substring(6)) + 1;
            Calendar c = Calendar.getInstance();
            c.set(y, m, d);
            altHolyday.add(sdf.format(c.getTime()));
        }
            
        if(getDayOfWeek(dayThanks1) == Calendar.SUNDAY || getDayOfWeek(dayThanks2) == Calendar.SUNDAY || getDayOfWeek(dayThanks3) == Calendar.SUNDAY || isHolidaySolar(dayThanks1) || isHolidaySolar(dayThanks2) || isHolidaySolar(dayThanks3)) {
            int y = Integer.parseInt(dayThanks3.substring(0, 4));
            int m = Integer.parseInt(dayThanks3.substring(4, 6)) - 1;
            int d = Integer.parseInt(dayThanks3.substring(6)) + 1;
            Calendar c = Calendar.getInstance();
            c.set(y, m, d);
            altHolyday.add(sdf.format(c.getTime()));
        }
        
        int childWeek = getDayOfWeek(dayChild); 
        
        if(childWeek == Calendar.SATURDAY) {
            int y = Integer.parseInt(dayChild.substring(0, 4));
            int m = Integer.parseInt(dayChild.substring(4, 6)) - 1;
            int d = Integer.parseInt(dayChild.substring(6)) + 2;
            Calendar c = Calendar.getInstance();
            c.set(y, m, d);
            altHolyday.add(sdf.format(c.getTime()));
        }
            
        if(childWeek == Calendar.SUNDAY) {
            int y = Integer.parseInt(dayChild.substring(0, 4));
            int m = Integer.parseInt(dayChild.substring(4, 6)) - 1;
            int d = Integer.parseInt(dayChild.substring(6)) + 1;
            Calendar c = Calendar.getInstance();
            c.set(y, m, d);
            altHolyday.add(sdf.format(c.getTime()));
        }
        
        return altHolyday.contains(date); 
        */
    }
    
    public HashMap<String, Tuple2<String, String>> init() {
    	HashMap<String, Tuple2<String, String>> holidays = new HashMap<String, Tuple2<String, String>>();
    	SortedSet<String> sortHoliday = new TreeSet<String>();
    	solarHolidayMap.put("0101", "신정"); solarHolidayMap.put("0301", "삼일절");
        solarHolidayMap.put("0505", "어린이날"); solarHolidayMap.put("0606", "현충일");
        solarHolidayMap.put("0815", "광복절"); solarHolidayMap.put("1003", "개천절");
        solarHolidayMap.put("1009", "한글날"); solarHolidayMap.put("1225", "성탄절");
 
        lunarHolidayMap.put("0101", "구정"); lunarHolidayMap.put("0102", "구정");
        lunarHolidayMap.put("0408", "석가탄신일"); lunarHolidayMap.put("0814", "추석");
        lunarHolidayMap.put("0815", "추석"); lunarHolidayMap.put("0816", "추석");
 
        int year = 2015;
        int endYear = 2030;
//        int year = 2019;
//        int endYear = 2020;
 
        Calendar c = Calendar.getInstance();
        c.set(year, 0, 1); // 1월 1일부터 시작
        
        String solarDate = "";
        String lunarDate = "";
        for(int i=year; i<=endYear;) {
            solarDate = getDateByString(c.getTime(), "");
            lunarDate = converSolarToLunar(solarDate, "");
 
            c.add(Calendar.DAY_OF_MONTH, 1);
 
            //	대체휴일 체크
            if(isHolidayAlternate(solarDate)) {
            	String day = solarDate.substring(0,4) + "-" + solarDate.substring(4,6) + "-" + solarDate.substring(6,8);
                System.out.println(day + " ==> " + "대체공휴일");
                holidays.put(day, new Tuple2<String, String>(day, "대체공휴일"));
                sortHoliday.add(day);
            }
            
            // 양력휴일 체크
            String solarMmdd = solarDate.substring(4,8);
            if(solarHolidayMap.containsKey(solarMmdd)) {
            	String day = solarDate.substring(0,4) + "-" + solarDate.substring(4,6) + "-" + solarDate.substring(6,8);
                System.out.println(day + " ==> " + solarHolidayMap.get(solarMmdd));
                holidays.put(day, new Tuple2<String, String>(day, solarHolidayMap.get(solarMmdd)));
                sortHoliday.add(day);
            }
 
            // 음력휴일 체크
            String lunarMmdd = lunarDate.substring(4,8);
            if(lunarHolidayMap.containsKey(lunarMmdd)) {
                // 음력 12월은 마지막날이 29일, 30일 계속 번갈아가면서 있으므로
                // 양력에서 하루를 빼준날이 구정시작하는 날짜이다.
                if(lunarMmdd.equals("0101")) {
                	String tmp = getDay(solarDate, -1);
                	String day = tmp.substring(0,4) + "-" + tmp.substring(4,6) + "-" + tmp.substring(6,8);
                	System.out.println(day + " ==> 구정");
                	holidays.put(day, new Tuple2<String, String>(day, "구정"));
                	sortHoliday.add(day);
                }
                String day = solarDate.substring(0,4) + "-" + solarDate.substring(4,6) + "-" + solarDate.substring(6,8);
                System.out.println(day + " ==> " + lunarHolidayMap.get(lunarMmdd));
                holidays.put(day, new Tuple2<String, String>(day, lunarHolidayMap.get(lunarMmdd)));
                sortHoliday.add(day);
            }
            
            year = c.get(Calendar.YEAR);
            if(year != i) {
                i++;
                System.out.println("");
            }
            if(i > endYear) break;
        } // end for_i
        
        
        // 	연속된 휴일을 시작과 종료로 연결
        for(int index = 0; index < sortHoliday.size()-1; index++) {
        	Object sortStart = sortHoliday.toArray()[index];
        	Object sortEnd = sortHoliday.toArray()[index+1];
        	Object start = holidays.get(sortStart)._1();
        	Object end = holidays.get(sortEnd)._1();
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date sDate = simpleDateFormat.parse(start.toString());
				Date eDate = simpleDateFormat.parse(end.toString());
				//	두 날짜의 차이를 계산
				int count = (int)( (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24));
				if(count == 1 && holidays.get(sortStart)._2().equals(holidays.get(end)._2())) {
					holidays.remove(end);
					sortHoliday.remove(end);
					holidays.put(sortStart.toString(), new Tuple2<String, String>(end.toString(), holidays.get(sortStart)._2()));
					index--;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        Iterator<String> holidayKeys = holidays.keySet().iterator();
        while(holidayKeys.hasNext()) {
        	String key = holidayKeys.next();
        	Tuple2<String, String> tuple = holidays.get(key);
        	System.out.println(key + " ~ " + tuple._1() + " ==> "  + tuple._2());
        }
        
        return holidays;
    }
    
    public static ArrayList<String> getCurrentWeek(String day) throws ParseException {
    	ArrayList<String> list = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(day);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
		calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeekDay);
		
		String[] days = {"일","월","화","수","목","금","토"};
		for (int i = 0; i < days.length; i++) {
			list.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
    }
    
    //	종료날짜는 포함하지 않는다.
    public static List<String> getDateListEndDateNotInclude(String startDate, String endDate) throws ParseException{
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calendar = Calendar.getInstance();
    	calendar.clear();
    	List<String> holidayList = Lists.newArrayList();
    	
    	Date sDate = simpleDateFormat.parse(startDate);
    	Date eDate = simpleDateFormat.parse(endDate);

    	//	두 날짜의 차이를 계산
    	int count = (int)( (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24));
    	calendar.setTime(sDate);
    	
    	//	차이가 0 보다 큰 경우에는 시작날짜 추가
    	if(count >= 0) holidayList.add(startDate);
    	//	시작일과 종료일이 1보다 큰 경우에는 날짜를 모두 등록
    	for(int dayCount = 1; dayCount <= count; dayCount++) {
    		calendar.add(Calendar.DAY_OF_MONTH, 1);
    		Date time = calendar.getTime();
    		String day = simpleDateFormat.format(time);
    		holidayList.add(day);
    	}
    	return holidayList;
    }
    
    public static Tuple2<ArrayList<String>, Map<String, Integer>> getStartDayEndDay(List<String> holidays, String startDay, String endDay, String dayOfWeek) throws ParseException {
    	
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = originalFormat.parse(startDay);
		Date endDate = originalFormat.parse(endDay);		//	종료일짜를 출석일 제일 마지막 날짜로 초기화
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		
		//	종료 날짜는 출석일 제일 마지막 날짜의 다음 달 말일까지 계산
		calendar.setTime(endDate);
		calendar.add(Calendar.MONTH, 1);
		int monthEndDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, monthEndDay);
		endDate = calendar.getTime();
	
		//	시작 날짜는 수강 시작일
		calendar.setTime(startDate);
//		시작 날짜가 포함된 주의 첫번째 날짜 계산
//		calendar.setTime(startDate);
//		int dayOfWeekDay = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
//		calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeekDay);
		
		
		//	두 날짜의 차이를 계산
		int count = (int)( (endDate.getTime() - calendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
		
		SimpleDateFormat mapFormat = new SimpleDateFormat("yyyy년 MM월");
		Map<String, Integer> monthTotalCountMap = new TreeMap<String, Integer>();
		ArrayList<String> subjectDays = new ArrayList<String>();
		for (int i = 0; i <= count; i++) {
			Date time = calendar.getTime();
			String day = originalFormat.format(time);
			
			//	과정의 수강요일만 포함하며 휴강일은 카운트에서 제외
			if(dayOfWeek != null && dayOfWeek.charAt(calendar.get(Calendar.DAY_OF_WEEK)-1) == '1' &&
					!holidays.contains(day)){
				
				//	해당 월에 대한 총 카운트를 저장
				String monthMap = mapFormat.format(time);
				int monthMapKey = (monthTotalCountMap.containsKey(monthMap)) ? monthTotalCountMap.get(monthMap)+1 : 1;
				monthTotalCountMap.put(monthMap, monthMapKey);
				subjectDays.add(day);
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return new Tuple2<ArrayList<String>, Map<String,Integer>>(subjectDays, monthTotalCountMap);
    }
    
    public static void main(String[] args) {
    	HolidayUtils holiday = new HolidayUtils();
    	holiday.init();
    }
}
