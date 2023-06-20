package com.example.monara_backend.service;

import com.example.monara_backend.dto.ShowroomFileDocument;
import com.example.monara_backend.model.Designer;
import com.example.monara_backend.repository.DesignerRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DesignerService {

    @Autowired
    private final DesignerRepo designerRepo;

    @Autowired
    public DesignerService(DesignerRepo designerRepo) {
        this.designerRepo = designerRepo;
    }

    @Autowired
    private ModelMapper modelMapper;

    public ShowroomFileDocument saveShowroomFile(ShowroomFileDocument showroomFileDocument){
        showroomFileDocument.save(modelMapper.map(showroomFileDocument,Designer.class));
        return showroomFileDocument;
    }

    public List<ShowroomFileDocument>getAllDesigner(){
       List<Designer>designerList = (List<Designer>) designerRepo.findAll();
       return modelMapper.map(designerList,new TypeToken<List<ShowroomFileDocument>>(){}.getType());
    }

}
