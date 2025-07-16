package com.contactpro.contactpro.dto;

public class AttachmentDTO {

    private Long id;
    private String nameAttachment;
    private String typeAttachment;
    private Long contractId;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAttachment() {
        return nameAttachment;
    }

    public void setNameAttachment(String nameAttachment) {
        this.nameAttachment = nameAttachment;
    }

    public String getTypeAttachment() {
        return typeAttachment;
    }

    public void setTypeAttachment(String typeAttachment) {
        this.typeAttachment = typeAttachment;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
