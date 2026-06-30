package com.ariel.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ariel.data.BancoUsuario;
import com.ariel.model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Garante que requisições e respostas processem acentos corretamente
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Seu código vem aqui
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");
        Usuario usuarioLogado = null;

        for (Usuario u : BancoUsuario.lista) {
            if (u.getUsuario().equals(usuarioDigitado) && u.getUsuario().equals(senhaDigitada)) {
                usuarioLogado = u;
                break;
            }
        }

        if (usuarioLogado != null) {
            // login esta autorizado
            request.getRequestDispatcher("bemvindo.jsp").forward(request, response);
        } else {
            // login nao esta autorizado
            request.setAttribute("mensagemErro", "Usuário ou senha inválidos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
