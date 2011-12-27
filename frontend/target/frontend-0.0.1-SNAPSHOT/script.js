var count = 0;
        var array = [];

        function addTask(){
            var tasks = document.getElementById("select");
            var flag = 0;
            for(var i = 0; i<array.length; i++){
                if (array[i] == tasks.value){
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                var hours_div = document.getElementById("hours_done")
                var tasks_div = document.getElementById("tasks");
                var element = document.createElement("span");
                count++;
                var index = tasks.selectedIndex;
                element.innerHTML = count+". " + tasks.options[index].text;
                array[array.length] = tasks.value;
                tasks_div.appendChild(element);
                element = document.createElement("input");
                element.type="hidden";
                element.name = "hidden"+count;
                element.value = tasks.value;
                tasks_div.appendChild(element);
                element = document.createElement("br");
                tasks_div.appendChild(element);
                element = document.createElement("span");
                element.innerHTML = count+". ";
                hours_div.appendChild(element);
                element = document.createElement("input");
                element.type="text";
                element.name = "hours"+count;
                hours_div.appendChild(element);
                element = document.createElement("br");
                hours_div.appendChild(element);
            }
        }


