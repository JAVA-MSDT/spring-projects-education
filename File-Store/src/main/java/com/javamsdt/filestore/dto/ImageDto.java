package com.javamsdt.filestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImageDto {

  private long id;
  private Resource content;
  private String name;
  private String location;

}
