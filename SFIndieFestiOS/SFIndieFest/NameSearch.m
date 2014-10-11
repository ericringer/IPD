//
//  NameSearch.m
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "NameSearch.h"
@implementation NameSearch

-(void)performSearch:(NSString *)search ViewController:(UIViewController *)controller ResultBlock:(void (^) (NameSearchResult * result)) block{
    if (!block)
        return;
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Name Search" message:@"Please wait..." delegate:self cancelButtonTitle:nil otherButtonTitles: nil];
    UIActivityIndicatorView * indicator = [self getAlertIndicator:controller Alert:alert];
    
    [alert show];
    [indicator startAnimating];
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        NameSearchResult * searchResult  = [self performSearch:search];
        dispatch_async(dispatch_get_main_queue(), ^{
            [indicator stopAnimating];
            [alert dismissWithClickedButtonIndex:0 animated:true];
            block(searchResult);
            
        });
    });
    
}

-(UIActivityIndicatorView *)getAlertIndicator:(UIViewController *)controller Alert:(UIAlertView * )alert{
    
    UIActivityIndicatorView *indicator = [[UIActivityIndicatorView alloc]initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    
    indicator.frame = CGRectMake(0.0, 0.0, 40.0, 40.0);
    indicator.center = controller.view.center;
    
    [alert setValue:indicator forKey:@"accessoryView"];
    [indicator bringSubviewToFront:controller.view];
    [UIApplication sharedApplication].networkActivityIndicatorVisible = TRUE;
    return indicator;
    
}
-(NameSearchResult *)performSearch:(NSString *)search{
    NSString* encodedSearch = [search stringByAddingPercentEscapesUsingEncoding:
                            NSUTF8StringEncoding];
    NSString * url = [[NSString alloc]initWithFormat:@"http://imdb.wemakesites.net/api/1.0/get/name/?q=%@",encodedSearch];
    NSData * response = [self performGET:url];
    
    return [[NameSearchResult alloc] initWithResponse:response];
}
-(NSData *) performGET:(NSString *) url{
    NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
    [request setURL:[NSURL URLWithString:url]];
    [request setHTTPMethod:@"GET"];
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    
    NSURLResponse *response;
    NSError *error;
    
    NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
    NSLog(@"Error: %@",error);
    return  data;
}


@end
