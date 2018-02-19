import React from "react";
import {Grid, Nav, Navbar, NavItem} from 'react-bootstrap'
import {Button, Form, FormControl} from 'react-bootstrap-form'
import {Link} from "react-router-dom";

export default class Home extends React.Component {
    constructor(props) {
        super(props)
        this.logOut = this.logOut.bind(this)
    }

    logOut() {
        var self = this
        fetch('http://localhost:8080/index?logout', {
            method: 'POST',
            credentials: 'same-origin'
        })
            .then(function () {
                window.location.assign('http://localhost:8080/index')
            })


        }


    render() {
        var role
        if (this.props.role == "ROLE_USER") {
            role = <span>Developer</span>
        } else {
            role = <span>Manager</span>
        }
        var name = this.props.user.name + " " + this.props.user.surname;
        return (
            <div>
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" >Hello {role} {name}</a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li><Link to='/project'>Your Projects</Link></li>
                        </ul>
                        <button  class="btn btn-default" onClick={this.logOut}>Log out</button>

                    </div>
                </nav>

            </div>

        )
    }
}