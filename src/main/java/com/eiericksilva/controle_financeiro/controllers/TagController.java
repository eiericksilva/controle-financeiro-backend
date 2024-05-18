package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eiericksilva.controle_financeiro.entities.Tag;
import com.eiericksilva.controle_financeiro.services.TagService;

@RestController
@RequestMapping("/tags")
@CrossOrigin
public class TagController {
    @Autowired
    private TagService tagService;

    /*CREATE*/
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    /*READ*/
    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAllTags();
    }

    @GetMapping("/{tagId}")
    public Tag findById(@PathVariable Long tagId) {
        return tagService.findTagById(tagId);
    }

    /*UPDATE*/
    @PutMapping("/{tagId}")
    public Tag edit(@PathVariable Long tagId, @RequestBody Tag tag) {
        return tagService.editTag(tagId, tag);
    }

    /*DELETE*/
    @DeleteMapping("/{tagId}")
    public void deleteById(@PathVariable Long tagId, @RequestBody Tag tag) {
        tagService.deleteTagById(tagId);
    }


}
