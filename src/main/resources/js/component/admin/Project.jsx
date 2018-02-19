import React, {Component} from 'react'
import TaskList from "./TaskList.jsx"
import FilteredTaskList from "./FilteredTaskList.jsx"
import User from "./User.jsx"
export default class Project extends React.Component {
    constructor(props) {
        super(props)
        this.state = {infoIsOpen: false, tasksIsOpen: false, filter: false}
        this.handleInfoClick = this.handleInfoClick.bind(this)
        this.handleTasksClick = this.handleTasksClick.bind(this)
        this.handleFilter = this.handleFilter.bind(this)
    }

    handleInfoClick() {
        this.setState({infoIsOpen: !this.state.infoIsOpen})

    }

    handleTasksClick() {
        this.setState({tasksIsOpen: !this.state.tasksIsOpen})

    }

    handleFilter() {
        this.setState({filter: !this.state.filter})
    }

    render() {
        var addDeveloper
        var filterTasks
        var tasks
        if (this.props.role == "ROLE_ADMIN") {
            addDeveloper = <User projectId={this.props.project.id}/>
            tasks = this.state.tasksIsOpen &&
                <TaskList projectId={this.props.project.id}/>
        } else {
            if (this.state.tasksIsOpen) {

                if (this.state.filter) {
                    filterTasks = <button onClick={this.handleFilter}>Show all tasks</button>
                    tasks = <FilteredTaskList  projectId={this.props.project.id}/>
                } else {
                    filterTasks = <button onClick={this.handleFilter}>Show my tasks</button>
                    tasks = <TaskList projectId={this.props.project.id}/>
                }

            }
        }


        const info = this.state.infoIsOpen &&
            <div>
                <section>{this.props.project.description}</section>
                {addDeveloper}
            </div>

        return (
            <div>
                <h1>{this.props.project.name}</h1>
                <button onClick={this.handleInfoClick}>Info</button>
                <button onClick={this.handleTasksClick}>Tasks</button>
                {info}
                {tasks}
                {filterTasks}
            </div>

        )
    }
}



