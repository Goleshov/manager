import React, {Component} from 'react'
import toastr from 'toastr'
export default class NewProject extends React.Component {
    constructor(props) {
        super(props)
        this.state = {name: '', description: ''}
        this.createProject = this.createProject.bind(this)
        this.changeName =this.changeName.bind(this)
        this.changeDescription=this.changeDescription.bind(this)
    }

    createProject() {
        var self = this
        if (self.state.name!== '' && self.state.description!=='' ) {
            fetch('http://localhost:8080/project/create', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Cache': 'no-cache'
                },
                body: JSON.stringify({name: this.state.name, description: this.state.description}),
                credentials: 'same-origin'
            })
                .then(
                    function () {
                        self.props.onChange();
                    }
                )
        }else {
            toastr.error('Fill in new projects fields!')
        }
    }

    changeName(event){

        this.setState({name : event.target.value})
    }
    changeDescription(event){
        this.setState({description : event.target.value})
    }

    render() {
        return (

            <div class="col-sm-6">


                    <div class="control-group">
                        <label class="control-label" for="inputName">Name</label>
                        <div class="controls">
                            <input
                                onChange={this.changeName}
                                type="text" id="inputName" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputDescription">Description</label>
                        <div class="controls">
                            <textarea
                                onChange={this.changeDescription}
                                rows="3" id="inputDescription" placeholder="Description">

                            </textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button
                                onClick={this.createProject}
                                class="btn">Create project</button>
                        </div>
                    </div>

            </div>
        )
    }

}