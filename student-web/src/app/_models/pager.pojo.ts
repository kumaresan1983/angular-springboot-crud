/*******************************************************
**  ©Copyright 2018 Dagang Net Technologies Sdn Bhd.  **
**  Author : PRAVEEN                                  **
*******************************************************/

export class PagerPojo {
    public total: number;   // total number of records
    public perPage = 10;
    public page = 1;
    public firstResult: number;
    public totalPage: number[];
    public firstPage: boolean;
    public lastPage: boolean;
    public showPerPage = true; // this is used to show/hide the per page drop down
}
