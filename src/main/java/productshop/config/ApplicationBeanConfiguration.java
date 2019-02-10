package productshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productshop.util.FileIoUtil;
import productshop.util.FileIoUtilImpl;
import productshop.util.ValidatorUtil;
import productshop.util.ValidatorUtilImpl;

import java.io.File;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public FileIoUtil fileIoUtil() {
        return new FileIoUtilImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }
}
