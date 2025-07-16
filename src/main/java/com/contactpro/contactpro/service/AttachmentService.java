package com.contactpro.contactpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactpro.contactpro.model.Attachment;
import com.contactpro.contactpro.repository.AttachmentRepository;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    public Optional<Attachment> findById(Long id) {
        return attachmentRepository.findById(id);
    }

    public Attachment create(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    public Attachment update(Long id, Attachment updatedAttachment) {
        return attachmentRepository.findById(id)
                .map(existing -> {
                    existing.setNomeAttachment(updatedAttachment.getNomeAttachment());
                    existing.setType(updatedAttachment.getType());
                    existing.setFile(updatedAttachment.getFile());
                    existing.setContract(updatedAttachment.getContract());
                    return attachmentRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Anexo não encontrado"));
    }

    public void delete(Long id) {
        attachmentRepository.deleteById(id);
    }
}
