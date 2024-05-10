package com.eiericksilva.controle_financeiro.services;

import com.eiericksilva.controle_financeiro.entities.Tag;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    /*CREATE*/
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /*READ*/
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public Tag findTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException(tagId));
    }
    /*UPDATE*/


    public Tag editTag(Long tagId, Tag tag) {
        Tag tagToUpdate = tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException(tagId));
        tagToUpdate.setName(tag.getName());

        return tagRepository.save(tagToUpdate);
    }

    /*DELETE*/
    public void deleteTagById(Long tagId) {
        tagRepository.delete(tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException(tagId)));
    }

}
