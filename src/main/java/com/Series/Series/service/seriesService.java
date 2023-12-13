package com.Series.Series.service;

import com.Series.Series.entity.series;
import com.Series.Series.exception.ResourceNotFoundException;
import com.Series.Series.payload.seriesdto;
import com.Series.Series.repository.seriesrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class seriesService {

    @Autowired
    public seriesrepository seriesrepo;

  public void save(seriesdto dto){

      series ser = new series();

      ser.setId(dto.getId());
      ser.setName(dto.getName());
      ser.setYearOfRelease(dto.getYearOfRelease());

      seriesrepo.save(ser);
  }

  public void delete(long id){
       series ser = seriesrepo.findById(id).orElseThrow(
              ()-> new ResourceNotFoundException("Record not found with id"+id)
      );

       seriesrepo.deleteById(id);
  }

  public series find(long id ){

    series ser = seriesrepo.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Record not found with id" + id)
    );
    return ser;

//
//      series se =   seriesrepo.findById(id).orElseThrow(
//              ()-> new ResourceNotFoundException("Record not found with id"+id)
//      );


  }

  public void updatedsave(series se){
      seriesrepo.save(se);
  }

  public List<seriesdto> read(int pageNo, int pageSize, String sortBy , String sortDir){


      Sort sort =  sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

      Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

      Page<series> all = seriesrepo.findAll(pageable);
      List<series> lall = all.getContent();
      List<seriesdto> fetched = lall.stream().map(p->maptodto(p)).collect(Collectors.toList());;
      return  fetched;


  }

  public seriesdto maptodto(series ses){
      seriesdto dto = new seriesdto();
      dto.setId(ses.getId());
      dto.setName(ses.getName());
      dto.setYearOfRelease(ses.getYearOfRelease());
      return dto;

  }



}
