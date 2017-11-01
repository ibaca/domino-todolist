package com.progressoft.domino.tutorial.todolistbackendsprintboot;

import com.progressoft.brix.domino.sample.items.server.handlers.*;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/")
@CrossOrigin(origins = {"*"})
public class TodoListController {

    @RequestMapping(value = LoadItemsRequest.PATH, method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return ResponseEntity.ok(new LoadItemsHandler().handleRequest(new LoadItemsRequest()));
    }

    @RequestMapping(value = AddItemRequest.PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItem(@RequestBody AddItemRequest request) {
        return ResponseEntity.ok(new AddItemHandler().handleRequest(request));
    }

    @RequestMapping(value = RemoveRequest.CLEAR, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity clear(@RequestBody RemoveRequest request) {
        return ResponseEntity.ok(new ClearAllHandler().handleRequest(request));
    }

    @RequestMapping(value = RemoveRequest.REMOVE_DONE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeDone(@RequestBody RemoveRequest request) {
        return ResponseEntity.ok(new ClearDoneHandler().handleRequest(request));
    }

    @RequestMapping(value = ToggleItemRequest.PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity toggleItem(@RequestBody ToggleItemRequest request) {
        return ResponseEntity.ok(new ToggleItemHandler().handleRequest(request));
    }
}
