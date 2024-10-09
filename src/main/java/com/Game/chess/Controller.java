package com.Game.chess;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/cell-data")
    public Map<String, String> getCellData(@RequestParam int row, @RequestParam int col) {
        String info = service.getInfoForCell(row, col);
        Map<String, String> response = new HashMap<>();
        response.put("info", info);
        return response;
    }

    @GetMapping("/valid-moves")
    public List<Coordinate> validMoves(@RequestParam int row, @RequestParam int col) {
       List<List<Integer>> coordinates = service.getValidMoves(row, col);
       List<Coordinate> response = new ArrayList<>();
       for (List<Integer> coordinate : coordinates) {
           response.add(new Coordinate(coordinate.get(0), coordinate.get(1)));
       }
    return response;
   }

   @PostMapping("/move-piece")
   public void MovePiece(@RequestBody CoordinateRequest coordinates) {
      Coordinate start = coordinates.getStart();
      Coordinate end = coordinates.getEnd();
      service.makeMove(start, end);
   }
   
   @PostMapping("/reset")
   public void reset() {
       service.Reset();
   }

   
   
    
}
