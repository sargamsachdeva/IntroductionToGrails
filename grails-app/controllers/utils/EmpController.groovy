package utils

import org.grails.datastore.mapping.query.Query

class EmpController {

    def index() {}

    def list = {

        List<Employee> employeeList = Employee.createCriteria().list {

            and {

                between("age",18,50)
                eq("firstName","abc")
            }
        }

        render "Result -> ${employeeList.size()} ${employeeList.firstName} ${employeeList.age}"
    }

   def listPaginate = {

       List<Employee> employeeList = Employee.createCriteria().list(max:2,offset:0) {

           ilike("firstName","a%")
           le("age",30)
       }

       render "Result-> ${employeeList.size()} ${employeeList*.id}"
       Integer totalCount = employeeList.totalCount
       render "<br />"
       render totalCount
   }

    def listPaginateUsingListDistinct = {

        List<Employee> employeeList = Employee.createCriteria().listDistinct {

            ilike("firstName","a%")
            le("age",30)
            maxResults 2
            firstResult 1
        }

        render "Result-> ${employeeList.size()} ${employeeList*.id} ${employeeList.age}"
     }

    def empCount = {

        List<Employee> employeeList = Employee.createCriteria().count {

            ilike("firstName","a%")
            le("age",30)
        }

        render "Result-> ${empCount}"
    }

    def and = {

        List<Employee> employeeList = Employee.createCriteria().list {

            and {
                between("age",23,50)
                'branch' {
                    eq("name","b1")
                }
            }
        }

        render "Result-> ${employeeList*.age} ${employeeList*.branch*.name}"
    }

    def distinct = {

        List<Integer> empAges = Employee.createCriteria().list {

            projections {

                distinct("age")
            }

            ilike("firstName","a%")
            le("age",40)
        }

        render "Result-> ${empAges}"
    }

    def rowCount = {

        Integer empCount = Employee.createCriteria().get {

            projections {

                rowCount()
            }

            ilike("firstName","a%")
            le("age",40)
        }

        render "Result-> ${empCount}"
    }

    def groupProperty= {

        List result = Employee.createCriteria().list {

            projections {
                groupProperty("branch")
                sum("age")
                rowCount()
            }
        }
        render "REsult-> ${result}"
    }

    def executeUpdate={

        Employee employee = Employee.get(1)

        String firstName=employee.firstName
        Employee.executeUpdate("update Employee as emp set emp.firstName=:firstName where emp.id=:id",[firstName:"test",id:1.toLong()])
        employee.refresh()

        render "firstname before ${firstName} -: after updation ${employee.firstName}"
    }

}
