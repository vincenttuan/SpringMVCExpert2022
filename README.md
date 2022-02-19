# 第二階段：Java 企業產訓 SpringMVC 篇
<b><a href="https://github.com/vincenttuan/SpringCoreExpert2022">第一階段：Java 企業產訓 SpringCore 篇：Session 01 ~ Session 07</a></b><p />
<a href="./pom.xml">Maven 配置檔：pom.xml</a><br />
<a href="https://github.com/vincenttuan/SpringMVCExpert2022/blob/main/src/main/webapp/WEB-INF/web.xml">web.xml 部署檔：web.xml</a><br /><a href="https://github.com/vincenttuan/SpringMVCExpert2022/blob/main/src/main/webapp/WEB-INF/springmvc-servlet.xml">SpringMVC 配置檔：springmvc-servlet.xml</a><p />
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
    <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session11/">Session 11 - SpringMVC 表單資料綁定 ModelAttribute</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session12/">Session 12 - SpringMVC 表單資料綁定 Lab 練習</a><br />
  </li>
    <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/webapp/WEB-INF/views/session12">Session 12 - SpringMVC 表單資料綁定 Lab 練習(view)</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session13/">Session 13 - SpringMVC 表單資料驗證 JSR-303</a><br />
  </li>
    <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session14/">Session 14 - SpringMVC 表單資料客製化驗證</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session15/">Session 15、16 - SpringMVC 與 JdbcTemplate</a><br />
  </li>
  <li>
      <a href="https://github.com/vincenttuan/SpringMVCExpert2022/tree/main/src/main/java/spring/mvc/session17/">Session 17、18 - 統一例外處理</a><br />
  </li>
    <li>
      <a href="https://github.com/vincenttuan/SpringBoot-PSI-20211018">Session 19、20 - 專題：簡易進銷存管理（SpringBoot版）</a><br />
  </li>
</ul>

<hr />
問題探討：<br />
1. springmvc在接收時間類型的時候，報 Failed to convert value of type 'java.lang.String' to required type 'java.util.Date'的錯誤，應該怎麼解決呢?<br />
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")  // 返回時間類型<br />
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收時間類型<br />
private Date createTime; // 建檔日期<p />

小技巧：讓 jsp 可以支援所有 HTTP 方法
<%@ page isErrorPage="true" %>

SQLite 外鍵約束關聯注意：
官方文件：外鍵約束默認是禁用的（為了向後兼容），所以必須分別為每個數據庫連接啟用。
所以可以在刪除方法或其他更好的地方下達以下命令：
jdbcTemplate.execute("PRAGMA foreign_keys = ON");
