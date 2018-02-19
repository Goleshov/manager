import React, {Component} from 'react'
import toastr from 'toastr'

export default class Login extends React.Component {
    constructor(props) {
        super(props)
        this.state = { email: '', password: ''}
        this.getUser=this.getUser.bind(this)
        this.changePassword = this.changePassword.bind(this)
        this.changeEmail = this.changeEmail.bind(this)
    }

    getUser() {
        var self = this
        fetch('http://localhost:8080/index' +'?username=' + self.state.email +"&password=" + self.state.password , {
            method: 'POST',
            credentials: 'same-origin'
        }).then(function (response) {
            debugger;
            if (response.ok){
                window.location.assign('http://localhost:8080/index')
            }
            else {
                toastr.error("invalid email or password")
            }
        })
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
                        <div class="controls">
                            <button
                                onClick={this.getUser}
                                class="btn">Log In
                            </button>
                        </div>
                    </div>

                </div>

        )
    }
}
