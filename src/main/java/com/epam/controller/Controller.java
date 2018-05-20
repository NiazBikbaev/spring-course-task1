package com.epam.controller;

import com.epam.domain.Data;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Default comment.
 **/
@WebServlet("/form")
@SuppressWarnings("unchecked")
public class Controller extends HttpServlet {

    private final Map<String, Data> state = new HashMap();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(gson.toJson(getData()));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Data data = new Data();
        try (BufferedReader reader = req.getReader();) {
            data.setId(UUID.randomUUID().toString());
            data.setData(gson.fromJson(reader, Data.class).getData());
            state.put(data.getId(), data);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.getWriter().write(gson.toJson(getData()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader()) {
            Data newItem = gson.fromJson(reader, Data.class);
            if (!state.containsKey(newItem.getId())) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Data not found!");
                return;
            }
            state.put(newItem.getId(), newItem);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.getWriter().write(gson.toJson(getData()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader()) {
            Data toDelete = gson.fromJson(reader, Data.class);
            if (!state.containsKey(toDelete.getId())) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Data not found!");
                return;
            }
            state.remove(toDelete.getId());
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        resp.getWriter().write(gson.toJson(getData()));
    }

    private List<Data> getData() {
        return state.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
