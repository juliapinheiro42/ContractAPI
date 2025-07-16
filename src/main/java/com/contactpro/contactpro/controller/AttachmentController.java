package com.contactpro.contactpro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactpro.contactpro.dto.AttachmentDTO;
import com.contactpro.contactpro.model.Attachment;
import com.contactpro.contactpro.model.Contract;
import com.contactpro.contactpro.repository.ContractRepository;
import com.contactpro.contactpro.service.AttachmentService;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping
    public ResponseEntity<List<AttachmentDTO>> getAllAttachments() {
        List<AttachmentDTO> dtos = attachmentService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttachmentDTO> getAttachmentById(@PathVariable Long id) {
        Attachment attachment = attachmentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Anexo não encontrado"));
        return ResponseEntity.ok(convertToDTO(attachment));
    }

    @PostMapping
    public ResponseEntity<AttachmentDTO> createAttachment(@RequestBody AttachmentDTO dto) {
        Attachment entity = convertToEntity(dto);
        Attachment saved = attachmentService.create(entity);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttachmentDTO> updateAttachment(@PathVariable Long id, @RequestBody AttachmentDTO dto) {
        Attachment updatedEntity = convertToEntity(dto);
        Attachment updated = attachmentService.update(id, updatedEntity);
        return ResponseEntity.ok(convertToDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão: Entity → DTO
    private AttachmentDTO convertToDTO(Attachment attachment) {
        AttachmentDTO dto = new AttachmentDTO();
        dto.setId(attachment.getId());
        dto.setNameAttachment(attachment.getNomeAttachment());
        dto.setTypeAttachment(attachment.getType());
        dto.setContractId(attachment.getContract() != null ? attachment.getContract().getId() : null);
        return dto;
    }

    // Conversão: DTO → Entity
    private Attachment convertToEntity(AttachmentDTO dto) {
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        attachment.setNomeAttachment(dto.getNameAttachment());
        attachment.setType(dto.getTypeAttachment());

        if (dto.getContractId() != null) {
            Contract contract = contractRepository.findById(dto.getContractId())
                    .orElseThrow(() -> new RuntimeException("Contrato não encontrado para o anexo"));
            attachment.setContract(contract);
        }

        return attachment;
    }
}
