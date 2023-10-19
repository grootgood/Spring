package org.galapagos.domain.local;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data   
public class LocalResult {

   @SerializedName("meta")
   Meta meta;

   @SerializedName("documents")
   List<Local> locals;
}