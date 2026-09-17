package com.keeper.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keeper.dao.KeeperDao;
import com.keeper.model.Keeper;

@WebServlet("/NotesServlet")
public class NotesServlet extends HttpServlet {
	
	KeeperDao dao = new KeeperDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		
		String action = req.getParameter("action");
		
		
		if("delete".equals(action)) {
			
			int noteId = Integer.parseInt(req.getParameter("noteid"));
			dao.removeNote(noteId);
			res.sendRedirect("NotesServlet");
			return;
		}
		
		List<Keeper> notes = dao.getAllNotes();
		
		req.setAttribute("notes", notes);
		req.getRequestDispatcher("keeper.jsp").forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		
		String title = req.getParameter("title");
		String note = req.getParameter("note");
		
		Keeper n = new Keeper();
		n.setTitle(title);
		n.setNote(note);
		
		dao.saveNotes(n);
		
		res.sendRedirect("NotesServlet");
		
		
	}

}
