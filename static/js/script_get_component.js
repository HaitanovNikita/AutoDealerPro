class ComponentCar{

    constructor(id,component,container){
        this.id = id;
        this.component = component;
        document.getElementById(container).innerHTML+=this.getComponent();
    }

    getComponent(){
        return `
        <option type="text" name = "`+this.component+` "value="`+this.id+`">`+this.component+`</option>
        `;
    }
}


    


