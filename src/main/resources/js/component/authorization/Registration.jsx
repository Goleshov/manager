import React, {Component} from 'react'
import toastr from 'toastr'
export default class Registration extends React.Component {
    constructor(props) {
        super(props)
        this.state = {name: '', surname: '', email: '', password: '', role: 'ROLE_ADMIN'}
        this.createUser = this.createUser.bind(this)
        this.changeRole = this.changeRole.bind(this)
        this.changeName = this.changeName.bind(this)
        this.changeSurname = this.changeSurname.bind(this)
        this.changePassword = this.changePassword.bind(this)
        this.changeEmail = this.changeEmail.bind(this)
    }

    createUser() {
        var self = this
        debugger;
        fetch('http://localhost:8080/registration/' + self.state.role, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Cache': 'no-cache'
            },
            body: JSON.stringify({
                name: self.state.name, surname: self.state.surname,
                email: self.state.email, password: self.state.password
            }),
            credentials: 'same-origin'
        }).then(function (response) {
            if (response.status === 400) {
                toastr.error("User with this email already exists")
            } else if (response.status === 409) {
                toastr.error("Invalid user data")
            } else {
                toastr.info("Success! To confirm registration check your email")
            }
        })
    }


    changeSurname(event) {
        this.setState({surname: event.target.value})
    }

    changeRole(event) {
        this.setState({role: event.target.value})

    }

    changeName(event) {
        this.setState({name: event.target.value})
    }

    changePassword(event) {
        this.setState({password: event.target.value})
    }

    changeEmail(event) {
        this.setState({email: event.target.value})
    }

    render() {
        return (
            <div align="center" class="col-sm-6">
                <div class="control-group">
                    <label class="control-label" for="inputName">Name</label>
                    <div class="controls">
                        <input
                            onChange={this.changeName}
                            type="text" id="inputName" placeholder="Input Name"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputSurname">Surname</label>
                    <div class="controls">
                        <input
                            onChange={this.changeSurname}
                            type="text" id="inputSurname" placeholder="Input Surname"/>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Email</label>
                        <div class="controls">
                            <input
                                onChange={this.changeEmail}
                                type="text" id="inputEmail" placeholder="Input Email"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputPassword">Password</label>
                        <div class="controls">
                            <input
                                onChange={this.changePassword}
                                type="password" id="inputPassword" placeholder="Input Password"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ChooseRoleManager">Manager</label>
                        <div class="controls">
                            <input
                                onChange={this.changeRole}

                                type="radio" name="role" checked id="ChooseRoleManager" value="ROLE_ADMIN"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="ChooseRoleDeveloper">Developer</label>
                        <div class="controls">
                            <input
                                onChange={this.changeRole}
                                type="radio" name="role" id="ChooseRoleDeveloper" value="ROLE_USER"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button
                                onClick={this.createUser}
                                class="btn">Registration
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}
