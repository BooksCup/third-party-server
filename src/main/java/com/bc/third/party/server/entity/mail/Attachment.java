package com.bc.third.party.server.entity.mail;

import java.io.File;

/**
 * 邮件附件
 *
 * @author zhou
 */
public class Attachment {

    private String fileName;
    private File file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}