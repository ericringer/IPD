//
//  MapViewController.m
//  SFIndieFest
//
//  Created by eric ringer on 9/22/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "MapViewController.h"


@interface MapViewController ()

@end

@implementation MapViewController
@synthesize mapView;
-(id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}
- (IBAction)btnDirections:(id)sender {
    
    //Prepare Map
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(37.785756, -122.430597);
    MKPlacemark *placemark = [[MKPlacemark alloc] initWithCoordinate:coordinate addressDictionary:nil];
    MKMapItem *mapItem = [[MKMapItem alloc] initWithPlacemark:placemark];
    [mapItem setName:@"New People Cinema"];
    [mapItem openInMapsWithLaunchOptions:nil];
}

- (void)viewDidAppear:(BOOL)animated
{
    //Load pin on map and auto display pin details
    MKCoordinateSpan span = MKCoordinateSpanMake(0.01f, 0.01f);
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(37.785756, -122.430597);
    MKCoordinateRegion region = {coordinate, span};
    
    MKPointAnnotation *annotation = [[MKPointAnnotation alloc] init];
    [annotation setCoordinate:coordinate];
    [annotation setTitle:@"New People Cinema"];
    [annotation setSubtitle:@"1746 Post St, San Francisco, CA 94115"];
    [self.mapView setRegion:region animated:YES];
    [self.mapView addAnnotation:annotation];
    [self.mapView selectAnnotation:annotation animated:YES];
}

- (void)mapView:(MKMapView *)myMapView regionDidChangeAnimated:(BOOL)animated
{
    
}

-(void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
