package com.mct.recipeManagement.service;

import com.mct.recipeManagement.model.Comment;
import com.mct.recipeManagement.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public String saveComment(Comment comment) {
        commentRepo.save(comment);
        return "Commented !!";
    }

    public Comment findComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElse(null);
        return comment;
    }

    public void removeCommment(Comment comment) {
        commentRepo.delete(comment);
    }


}
