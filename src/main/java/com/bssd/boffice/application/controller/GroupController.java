package com.bssd.boffice.application.controller;

import com.bssd.boffice.application.dto.request.CreateGroupRequest;
import com.bssd.boffice.application.dto.request.UpdateGroupRequest;
import com.bssd.boffice.application.dto.response.GroupResponse;
import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<GroupResponse> createGroup(
            @RequestBody @Valid CreateGroupRequest group
    ){
        return new ResponseEntity<>(groupService.create(group), HttpStatus.OK);
    }


    @GetMapping(value = "/group")
    public ResponseEntity<List<GroupResponse>> getAllGroup(){
        try{
            List<GroupResponse> groups = groupService.findAll();
            if(groups.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/group/{id}")
    public ResponseEntity<GroupResponse> updateGroup(@RequestBody @Valid UpdateGroupRequest updateGroupRequest, @PathVariable Long id){
        return new ResponseEntity<>(groupService.update(updateGroupRequest, id), HttpStatus.OK);
    }

}
