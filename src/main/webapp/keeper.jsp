<%@page import="com.keeper.model.Keeper"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.3.1/css/all.min.css" integrity="sha512-QeR2VH+lsBE5LSAe1Q5EnTBbe7XTBubt8dG93Y7gidSgdMCr8nVqKcfKAMyN96SV8KDbZVTDXChatu5G2KQGzg==" crossorigin="anonymous" referrerpolicy="no-referrer">
</head>
<body>
<% List<Keeper> notes = (List) request.getAttribute("notes"); %>
 <nav>
      <h1>Keeper</h1>
    </nav>

    <main>
      <div class="note-only-box">
        <div class="note-box">
          <form action="NotesServlet" method="post">
            <input type="text" name="title" placeholder="Title" />
            <textarea type="text" name="note" placeholder="Take a note"></textarea>

            <div class="btn-div"><button>Add</button></div>
          </form>
        </div>
      </div>


      <div class="notes">
        <div class="note-cards">
        
        <% if(notes != null) { 
  for(Keeper note : notes) {%>
          <div class="note-card">
            <h2><%= note.getTitle() %></h2>
            <p>
              <%= note.getNote() %>
            </p>
            <div class="icon"> <a  href="NotesServlet?action=delete&noteid=<%= note.getNoteId()%>" onclick="return confirm('Are you sure you want to delete this note?');" >   <i class="fa-solid fa-trash fa-lg"></i></a> </div>
          </div>
          <%  }%>
        </div>
      </div>
<%  }%>
    </main>
</body>
</html>