package com.dsumtsov.tacocloud.api.assembler;

import com.dsumtsov.tacocloud.api.controller.DesignTacoApiController;
import com.dsumtsov.tacocloud.api.model.TacoModel;
import com.dsumtsov.tacocloud.model.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoModelAssembler
        extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

    public TacoModelAssembler() {
        super(DesignTacoApiController.class, TacoModel.class);
    }

    @Override
    protected TacoModel instantiateModel(Taco taco) {
        return new TacoModel(taco);
    }

    @Override
    public TacoModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
