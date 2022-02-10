# Java 企業產訓 SpringMVC 篇
<a href="https://github.com/vincenttuan/SpringCoreExpert2022">Java 企業產訓 SpringCore 篇：Session 01 ~ Session 07</a>
<ul>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session08/">Session 08 - SpringMVC Controller 基礎</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session09/">Session 09 - SpringMVC ModelAndView</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session10/">Session 10 - SpringMVC ModelAndView Lab 練習</a><br />
  </li>
</ul>
<hr />
問題探討：<br />
1. springmvc在接收時間類型的時候，報 Failed to convert value of type 'java.lang.String' to required type 'java.util.Date'的錯誤，應該怎麼解決呢?<br />
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")  // 返回時間類型<br />
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收時間類型<br />
private Date createTime; // 建檔日期<br />
