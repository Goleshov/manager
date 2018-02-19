import React, {Component} from 'react'
import toastr from "toastr"

export default class User extends React.Component {
    constructor(props) {
        super(props)
        this.state = {user: '', name: '', surname: '',display: false}
        this.findUser = this.findUser.bind(this)
        this.changeName = this.changeName.bind(this)
        this.changeSurname = this.changeSurname.bind(this)
        this.addUserToProject = this.addUserToProject.bind(this)
        this.addUserToTask = this.addUserToTask.bind(this)
    }


    addUserToProject() {
        var self = this
        debugger;
        if (self.state.user !== '') {
            fetch('http://localhost:8080/project/user/add?projectId=' + self.props.projectId + '&userId=' + self.state.user.id, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Cache': 'no-cache'
                },
                // body: JSON.stringify({projectId: self.props.projectId, userId: self.state.user.id}),
                credentials: 'same-origin'
            }).then(function (response) {
                if (response.status === 400) {
                    toastr.error("User already exists in this project")
                }
                else {
                    self.setState({display: false, user:''})
                    toastr.info("User added successfully")
                }
            })
        } else {
            toastr.error("User is empty!")
        }


    }

    addUserToTask() {
        var self = this
        if (self.state.user.id !== 'undefined') {
            fetch('http://localhost:8080/task/user/add?taskId=' + self.props.taskId + '&userId=' + self.state.user.id, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Cache': 'no-cache'
                },
                credentials: 'same-origin'


            }).then(function (response) {
                if (response.status === 400) {
                    toastr.error("User already exists in this task")
                }
                else {
                    self.setState({display: false, user:''})
                    toastr.info("User added successfully")
                }
            })
        } else {
            toastr.error("User is empty!")

        }


    }

    changeName(event) {
        this.setState({name: event.target.value})
    }

    changeSurname(event) {
        this.setState({surname: event.target.value})
    }

    findUser() {
        var self = this
        if (self.state.name==='' || self.state.surname===''){
            toastr.error("user is empty")
        }
        else {
            fetch('http://localhost:8080/users/' + this.state.name + '/' + this.state.surname, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Cache': 'no-cache'
                },
                credentials: 'same-origin'
            })
                .then(
                    function (response) {

                        response.json().then(function (data) {
                            debugger
                            self.setState({user: data, display: true});

                        })

                    }
                )
        }
    }


    render() {
        var button
        if(this.state.display==true){
        if (this.props.taskId != null) {
            button = <button onClick={this.addUserToTask} type="submit" class="btn">Add Developer to task</button>
        } else {
            button = <button onClick={this.addUserToProject} type="submit" class="btn">Add Developer to project</button>

        }}

        return (
            <div>
                <h2>Add Developer</h2>
                <h3>Find Developer</h3>
                <input onChange={this.changeName} type="text" class="input-medium search-query" placeholder="Name"/>
                <input onChange={this.changeSurname} type="text" class="input-medium search-query"
                       placeholder="Surname"/>
                <button onClick={this.findUser} type="submit" class="btn">Find</button>

                <h3>{this.state.user.email}</h3>
                <h4>{this.state.user.name} {this.state.user.surname}</h4>
                {button}

            </div>
        )

    }
}