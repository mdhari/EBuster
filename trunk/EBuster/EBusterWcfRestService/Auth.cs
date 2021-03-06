﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Web.Security;
using System.Text;
using EBusterWcfRestService.Models;
using System.Web;
using System.IO;

namespace EBusterWcfRestService
{
    // Start the service and browse to http://<machine_name>:<port>/Service1/help to view the service's generated help page
    // NOTE: By default, a new instance of the service is created for each call; change the InstanceContextMode to Single if you want
    // a single instance of the service to process all calls.	
    [ServiceContract]
    [AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerCall)]
    // NOTE: If the service is renamed, remember to update the global.asax.cs file
    public class Auth
    {
        // TODO: Implement the collection resource that will contain the SampleItem instances
        UserAccountDBContext userAcctDBContext = new UserAccountDBContext();

        [WebGet(UriTemplate = "")]
        public List<SampleItem> GetCollection()
        {
            // TODO: Replace the current implementation to return a collection of SampleItem instances
            //accountDBContext.Accounts.Add(new Account("michaeldhari@gmail.edu", "test1234"));
            //accountDBContext.SaveChanges();
            return new List<SampleItem>() { new SampleItem() { Id = 1, StringValue = "Hello" } };
        }

        [WebInvoke(UriTemplate = "/Validate", Method = "POST")]
        public string ValidateUser(UserAccountModel acct)
        {
            System.Console.WriteLine("Account with Email:" + acct.Email + "and Password" + acct.Password);
            // attempt to add the account to the database
            UserAccount userAcct = userAcctDBContext.UserAccounts.Find(acct.Email);
            if (userAcct == null)
            {
                return "not found";
            }
            else if (userAcct.Password == acct.Password)
            {
                return "ok";
            }
            else
            {
                return "wrong password";
            }
        }

        [WebInvoke(UriTemplate = "/Register", Method = "POST")]
        public MembershipCreateStatus CreateUser(UserAccountModel acct)
        {
            System.Console.WriteLine("Account with Email:" + acct.Email + "and Password" + acct.Password);
            // attempt to add the account to the database
            userAcctDBContext.UserAccounts.Add(new UserAccount(acct.Email, acct.Password));

            try
            {
                userAcctDBContext.SaveChanges(); // persist it!
            }
            catch (System.Data.Entity.Infrastructure.DbUpdateException ex)
            {
                // if its an updateexception, we KNOW the user already exists
                return MembershipCreateStatus.DuplicateEmail;
            }
            // TODO: Add the new instance of SampleItem to the collection
            return MembershipCreateStatus.Success;

        }

        [WebGet(UriTemplate = "{id}")]
        public SampleItem Get(string id)
        {
            // TODO: Return the instance of SampleItem with the given id
            throw new NotImplementedException();
        }

        [WebInvoke(UriTemplate = "{id}", Method = "PUT")]
        public SampleItem Update(string id, SampleItem instance)
        {
            // TODO: Update the given instance of SampleItem in the collection
            throw new NotImplementedException();
        }

        [WebInvoke(UriTemplate = "{id}", Method = "DELETE")]
        public void Delete(string id)
        {
            // TODO: Remove the instance of SampleItem with the given id from the collection
            throw new NotImplementedException();
        }

    }
}
