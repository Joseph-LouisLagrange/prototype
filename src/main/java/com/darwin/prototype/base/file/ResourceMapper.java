package com.darwin.prototype.base.file;


import com.darwin.prototype.po.sys.FilePlan;

public interface ResourceMapper {
    String downloadMap(FilePlan filePlan);

    String previewMap(FilePlan filePlan);
}
